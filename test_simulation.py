import urllib.request
import urllib.error
import json
import time
import threading
import sys

BASE_URL = "https://monte-carlo-mcp-server-684569726907.us-central1.run.app"
SSE_URL = f"{BASE_URL}/sse"
POST_ENDPOINT = None
SESSION_INITIALIZED = False

def on_event(event_type, data):
    global POST_ENDPOINT, SESSION_INITIALIZED
    # print(f"Event: {event_type}, Data: {data}")

    if event_type == "endpoint":
        POST_ENDPOINT = BASE_URL + data
        print(f"Endpoint received: {POST_ENDPOINT}")
        initialize_session()
    elif event_type == "message":
        try:
            message = json.loads(data)
            if message.get("id") == 0 and "result" in message:
                print("Initialization confirmed!")
                SESSION_INITIALIZED = True
        except json.JSONDecodeError:
            pass
    elif event_type == "tool_result":
        print(f"Tool Result Received: {data[:100]}...") # Print first 100 chars

def initialize_session():
    if not POST_ENDPOINT:
        return
    
    payload = {
        "jsonrpc": "2.0",
        "id": 0,
        "method": "initialize",
        "params": {
            "protocolVersion": "2024-11-05",
            "capabilities": {},
            "clientInfo": {"name": "TestScript", "version": "1.0"}
        }
    }
    send_post(payload)

def trigger_simulation():
    if not POST_ENDPOINT:
        print("Cannot trigger simulation: No endpoint")
        return

    payload = {
        "jsonrpc": "2.0",
        "id": 1,
        "method": "tools/call",
        "params": {
            "name": "find_optimal_pit_window",
            "arguments": {}
        }
    }
    print("Triggering simulation...")
    send_post(payload)

def send_post(payload):
    try:
        req = urllib.request.Request(
            POST_ENDPOINT,
            data=json.dumps(payload).encode('utf-8'),
            headers={'Content-Type': 'application/json'}
        )
        with urllib.request.urlopen(req) as response:
            print(f"POST response: {response.status}")
    except urllib.error.URLError as e:
        print(f"POST failed: {e}")

def listen_sse():
    try:
        req = urllib.request.Request(SSE_URL, headers={'Accept': 'text/event-stream'})
        with urllib.request.urlopen(req) as response:
            print(f"Connected to SSE. Status: {response.status}")
            buffer = ""
            while True:
                chunk = response.read(1024).decode('utf-8')
                if not chunk:
                    print("SSE Connection closed by server")
                    break
                # print(f"Received chunk: {chunk}") # Debug raw data
                buffer += chunk
                while "\n\n" in buffer:
                    event_block, buffer = buffer.split("\n\n", 1)
                    process_event_block(event_block)
    except Exception as e:
        print(f"SSE Error: {e}")

def process_event_block(block):
    event_type = "message"
    data = ""
    for line in block.splitlines():
        if line.startswith("event: "):
            event_type = line[7:]
        elif line.startswith("data: "):
            data += line[6:]
    
    if data:
        on_event(event_type, data)

if __name__ == "__main__":
    # Start SSE listener in a separate thread
    t = threading.Thread(target=listen_sse)
    t.daemon = True
    t.start()

    # Wait for initialization
    print("Waiting for initialization...")
    start_wait = time.time()
    while not SESSION_INITIALIZED:
        if time.time() - start_wait > 30:
            print("Timeout waiting for initialization")
            sys.exit(1)
        time.sleep(0.5)

    # Trigger simulation
    trigger_simulation()
    
    # Wait for results
    print("Waiting for strategies...")
    start_sim = time.time()
    strategies_count = 0
    
    # We'll listen for up to 180 seconds and break after first strategy
    while time.time() - start_sim < 180:
        time.sleep(1)
        # If we have received at least one strategy, we can break early
        if FIRST_STRATEGY_LATENCY is not None: # Changed condition to use FIRST_STRATEGY_LATENCY
            break

    # After loop, print latency if captured
    if FIRST_STRATEGY_LATENCY is not None:
        print(f"First strategy latency: {FIRST_STRATEGY_LATENCY:.0f}ms")
