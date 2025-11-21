# Android API Integration Guide

**Server URL:** `https://surfiniaburger-monte-carlo-sim.hf.space/sse`

## Protocol Overview
The server uses the **Model Context Protocol (MCP)** over **Server-Sent Events (SSE)**.
This is not a standard REST API. You must maintain a persistent connection.

## Connection Flow

1.  **Connect:**
    *   Make a `GET` request to `<URL>`.
    *   Headers: `Accept: text/event-stream`.
    *   The server will stream events.

2.  **Receive Endpoint:**
    *   The first event will be of type `endpoint`.
    *   Data: `/messages?session_id=...` (relative path).
    *   **Store this URL.** You will send all POST requests here.

3.  **Initialize:**
    *   Send a JSON-RPC 2.0 `initialize` request to the endpoint URL.
    *   Wait for the `initialized` notification.

4.  **Call Tool:**
    *   Send a JSON-RPC 2.0 `tools/call` request.

## Available Tools

### `find_optimal_pit_window`
*   **Description:** Runs the Monte Carlo simulation to determine the best pit stop strategy.
*   **Arguments:** None.
*   **Returns:** String (Strategy description).

## Kotlin / OkHttp Implementation Example

Add `okhttp` and `okhttp-sse` to your `build.gradle`.

```kotlin
val client = OkHttpClient.Builder()
    .readTimeout(0, TimeUnit.MILLISECONDS) // Keep connection open
    .build()

val request = Request.Builder()
    .url("https://surfiniaburger-monte-carlo-sim.hf.space/sse")
    .header("Accept", "text/event-stream")
    .build()

val factory = EventSources.createFactory(client)
var postEndpoint: String? = null

val listener = object : EventSourceListener() {
    override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
        if (type == "endpoint") {
            postEndpoint = "https://surfiniaburger-monte-carlo-sim.hf.space" + data
            println("Endpoint received: $postEndpoint")
            // Trigger initialization here
        }
    }
    
    override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
        println("Error: ${t?.message}")
    }
}

factory.newEventSource(request, listener)
```

### Sending Requests (JSON-RPC)

Once you have the `postEndpoint`, send standard POST requests:

```kotlin
val json = """
{
    "jsonrpc": "2.0",
    "id": 1,
    "method": "tools/call",
    "params": {
        "name": "find_optimal_pit_window",
        "arguments": {}
    }
}
"""

val body = json.toRequestBody("application/json".toMediaType())
val postRequest = Request.Builder().url(postEndpoint!!).post(body).build()
client.newCall(postRequest).execute()
```
