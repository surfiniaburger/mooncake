package com.surfiniaburger.alora.data

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import com.surfiniaburger.alora.common.ResultState
import com.surfiniaburger.alora.common.ErrorType

@Singleton
class RaceStrategyRepository @Inject constructor(
    private val client: OkHttpClient,
    private val eventSourceFactory: EventSource.Factory
) {

    private val BASE_URL = "https://monte-carlo-mcp-server-684569726907.us-central1.run.app"
    private val sseUrl = "$BASE_URL/sse"
    private var postEndpoint: String? = null
    private var eventSource: EventSource? = null
    private val _isInitialized = MutableStateFlow(false)

    fun getRaceStrategy(): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        
        val request = Request.Builder()
            .url(sseUrl)
            .header("Accept", "text/event-stream")
            .build()

        val listener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                Log.d("RaceStrategyRepo", "SSE Connection Opened")
                // Check for 404 or 500 here if response is not successful, though onOpen usually implies 200 OK for SSE
                if (!response.isSuccessful) {
                     val errorType = when (response.code) {
                        404 -> ErrorType.NOT_FOUND
                        in 500..599 -> ErrorType.SERVER_ERROR
                        else -> ErrorType.GENERIC
                    }
                    trySend(ResultState.Error(errorType, "Connection failed: ${response.code}"))
                }
            }

            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                Log.d("RaceStrategyRepo", "Event received: type=$type, data=$data")
                when (type) {
                    "endpoint" -> {
                        postEndpoint = BASE_URL + data
                        // Still loading/initializing
                        initializeSession()
                    }
                    "initialized" -> {
                        _isInitialized.value = true
                        trySend(ResultState.Success("Session Initialized. Ready."))
                    }
                    "tool_result" -> {
                        try {
                            val content = JSONObject(data).getJSONObject("result").getString("content")
                            trySend(ResultState.Success(content))
                        } catch (e: Exception) {
                            Log.e("RaceStrategyRepo", "Failed to parse tool_result JSON", e)
                            trySend(ResultState.Error(ErrorType.GENERIC, "Could not parse result."))
                        }
                    }
                    "log", "notifications/message" -> {
                        try {
                            val content = JSONObject(data).optString("message", data)
                            trySend(ResultState.Success(content))
                        } catch (e: org.json.JSONException) {
                            trySend(ResultState.Success(data))
                        }
                    }
                    "error" -> {
                        Log.e("RaceStrategyRepo", "Received error event: $data")
                        trySend(ResultState.Error(ErrorType.GENERIC, data))
                    }
                    else -> {
                        Log.d("RaceStrategyRepo", "Unhandled event type: $type, data: $data")
                    }
                }
            }

            override fun onClosed(eventSource: EventSource) {
                Log.d("RaceStrategyRepo", "SSE Connection Closed")
                close()
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                Log.e("RaceStrategyRepo", "SSE Error: ${t?.message}", t)
                val errorType = if (t is IOException) ErrorType.NETWORK_ERROR else ErrorType.GENERIC
                
                // Check response code if available
                val finalErrorType = if (response != null) {
                     when (response.code) {
                        404 -> ErrorType.NOT_FOUND
                        in 500..599 -> ErrorType.SERVER_ERROR
                        else -> errorType
                    }
                } else {
                    errorType
                }
                
                trySend(ResultState.Error(finalErrorType, t?.message ?: "Connection failed."))
                close(t)
            }
        }

        eventSource = eventSourceFactory.newEventSource(request, listener)

        awaitClose {
            Log.d("RaceStrategyRepo", "Closing SSE connection.")
            eventSource?.cancel()
            _isInitialized.value = false
        }
    }

    private fun initializeSession() {
        val json = """
        {
            "jsonrpc": "2.0",
            "id": 0,
            "method": "initialize",
            "params": {
                "protocolVersion": "2024-11-05",
                "capabilities": {},
                "clientInfo": { "name": "AloraAndroid", "version": "1.0" }
            }
        }
        """
        sendPostRequest(json)
    }

    suspend fun triggerSimulation() {
        if (postEndpoint == null) {
            Log.e("RaceStrategyRepo", "Cannot trigger simulation, postEndpoint is null.")
            return
        }
        
        if (!_isInitialized.value) {
             Log.d("RaceStrategyRepo", "Waiting for initialization...")
             _isInitialized.first { it }
        }

        val strategies = listOf("1-stop", "2-stop", "3-stop")
        
        strategies.forEachIndexed { index, strategyName ->
            val json = """
            {
                "jsonrpc": "2.0",
                "id": ${index + 1},
                "method": "tools/call",
                "params": {
                    "name": "find_optimal_pit_window",
                    "arguments": {
                        "strategy_name": "$strategyName"
                    }
                }
            }
            """
            Log.d("RaceStrategyRepo", "Triggering simulation for strategy: $strategyName")
            sendPostRequest(json)
        }
    }

    private fun sendPostRequest(json: String) {
        val endpoint = postEndpoint ?: return
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder().url(endpoint).post(body).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("RaceStrategyRepo", "POST request failed", e)
                // Note: We can't easily emit to the Flow here since it's a separate call.
                // Ideally, we'd have a shared MutableSharedFlow for errors or events,
                // but for now, the SSE stream is the main source of truth for the UI.
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        Log.e("RaceStrategyRepo", "POST request got non-successful response: ${it.code}")
                    } else {
                        Log.d("RaceStrategyRepo", "POST request successful: ${it.body?.string()}")
                    }
                }
            }
        })
    }
}