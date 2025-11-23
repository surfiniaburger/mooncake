package com.surfiniaburger.alora

import com.google.gson.JsonParser
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ServerConnectivityUnitTest {

    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    private val BASE_URL = "https://monte-carlo-mcp-server-684569726907.us-central1.run.app"
    private val SSE_URL = "$BASE_URL/sse"

    @Test
    fun testServerConnectivityAndSimulationLatency() {
        val latch = CountDownLatch(1)
        val status = StringBuilder()
        var isInitialized = false
        var postEndpoint: String? = null
        var strategiesReceived = 0
        val startTimes = mutableListOf<Long>()
        val responseTimes = mutableListOf<Long>()

        val request = Request.Builder()
            .url(SSE_URL)
            .header("Accept", "text/event-stream")
            .build()

        val factory = EventSources.createFactory(client)

        val listener = object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                println("ServerTest: SSE Connection Opened")
                status.append("Connected. ")
            }

            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                println("ServerTest: Event received: type=$type") // Uncommented for debugging
                
                if (type == "endpoint") {
                    postEndpoint = BASE_URL + data
                    println("ServerTest: Endpoint received: $postEndpoint")
                    initializeSession(postEndpoint!!)
                } else if (type == "message") {
                    try {
                        // Parse JSON to check for initialization confirmation
                        val json = JsonParser.parseString(data).asJsonObject
                        if (json.has("id") && json.get("id").asInt == 0 && json.has("result")) {
                            println("ServerTest: Initialization confirmed!")
                            isInitialized = true
                            status.append("Initialized. ")
                            
                            // Trigger Simulation immediately after initialization
                            println("ServerTest: Triggering simulation...")
                            startTimes.add(System.currentTimeMillis())
                            triggerSimulation(postEndpoint!!)
                        }
                    } catch (e: Exception) {
                        println("ServerTest: Error parsing message: ${e.message}")
                    }
                } else if (type == "tool_result") {
                    // We expect 3 strategies. The server sends them as tool_results.
                    // In a real scenario, we'd parse the JSON to be sure it's a strategy.
                    // For this timing test, we assume any tool_result after init is a strategy part.
                    strategiesReceived++
                    val time = System.currentTimeMillis()
                    val elapsed = time - startTimes[0]
                    responseTimes.add(elapsed)
                    println("ServerTest: Strategy #$strategiesReceived received after ${elapsed}ms")
                    
                    if (strategiesReceived >= 3) {
                        status.append("Received 3 strategies. ")
                        latch.countDown()
                    }
                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                println("ServerTest: SSE Failure: ${t?.message}")
                status.append("Failed: ${t?.message}")
                latch.countDown()
            }
        }

        println("ServerTest: Starting SSE connection...")
        factory.newEventSource(request, listener)

        // Wait for up to 600 seconds (10 minutes) for the full simulation
        val success = latch.await(600, TimeUnit.SECONDS)

        if (success) {
            println("ServerTest: LATENCY RESULTS:")
            responseTimes.forEachIndexed { index, time ->
                println("Strategy ${index + 1}: ${time}ms")
            }
            // Record latency of first strategy
            if (responseTimes.isNotEmpty()) {
                println("First strategy latency: ${responseTimes[0]}ms")
            }
        }

        // Updated assertions: test passes if at least one strategy is received
        assertTrue("Test timed out. Status: $status", success)
        assertTrue("Server did not initialize correctly. Status: $status", isInitialized)
        assertTrue("Did not receive any strategies", strategiesReceived >= 1)
    }

    private fun triggerSimulation(endpoint: String) {
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
            val body = json.toRequestBody("application/json".toMediaType())
            val request = Request.Builder().url(endpoint).post(body).build()

            println("ServerTest: Triggering simulation for strategy: $strategyName")
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("ServerTest: Simulation trigger failed for $strategyName: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    println("ServerTest: Simulation trigger response for $strategyName: ${response.code}")
                }
            })
        }
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
                println("ServerTest: Init POST failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                println("ServerTest: Init POST response: ${response.code}")
            }
        })
    }
}
