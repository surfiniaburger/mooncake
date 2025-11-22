package com.surfiniaburger.alora

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ServerConnectivityTest {

    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    private val BASE_URL = "https://monte-carlo-mcp-server-684569726907.us-central1.run.app"
    private val SSE_URL = "$BASE_URL/sse"

    @Test
    fun testServerConnectivityAndInitialization() {
        val latch = CountDownLatch(1)
        val status = StringBuilder()
        var isInitialized = false
        var postEndpoint: String? = null

        val request = Request.Builder()
            .url(SSE_URL)
            .header("Accept", "text/event-stream")
            .build()

        val factory = EventSources.createFactory(client)

        val listener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                Log.d("ServerTest", "SSE Connection Opened")
                status.append("Connected. ")
            }

            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                Log.d("ServerTest", "Event received: type=$type, data=$data")
                
                if (type == "endpoint") {
                    postEndpoint = BASE_URL + data
                    Log.d("ServerTest", "Endpoint received: $postEndpoint")
                    // Once we have the endpoint, trigger initialization
                    initializeSession(postEndpoint!!)
                } else if (type == "message") {
                    try {
                        val json = JSONObject(data)
                        // Check for initialization confirmation (id: 0)
                        if (json.optInt("id") == 0 && json.has("result")) {
                            Log.d("ServerTest", "Initialization confirmed!")
                            isInitialized = true
                            status.append("Initialized. ")
                            latch.countDown() // Test passed!
                        }
                    } catch (e: Exception) {
                        Log.e("ServerTest", "Error parsing message JSON", e)
                    }
                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                Log.e("ServerTest", "SSE Failure", t)
                status.append("Failed: ${t?.message}")
                latch.countDown() // Fail the test
            }
        }

        Log.d("ServerTest", "Starting SSE connection...")
        factory.newEventSource(request, listener)

        // Wait for up to 10 seconds for the flow to complete
        val success = latch.await(10, TimeUnit.SECONDS)

        assertTrue("Test timed out. Status: $status", success)
        assertTrue("Server did not initialize correctly. Status: $status", isInitialized)
    }

    private fun initializeSession(endpoint: String) {
        val json = """
        {
            "jsonrpc": "2.0",
            "id": 0,
            "method": "initialize",
            "params": {
                "protocolVersion": "2024-11-05",
                "capabilities": {},
                "clientInfo": { "name": "AloraAndroidTest", "version": "1.0" }
            }
        }
        """
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder().url(endpoint).post(body).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ServerTest", "Init POST failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("ServerTest", "Init POST response: ${response.code}")
            }
        })
    }
}
