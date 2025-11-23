import urllib.request
import urllib.error
import json
import time
import threading
import sys

class SimulationTester:
    def __init__(self, base_url):
        self.base_url = base_url
        self.sse_url = f"{base_url}/sse"
        self.post_endpoint = None
        self.session_initialized = False
        self.first_strategy_latency = None
        self.start_time = None
        self.running = False

    def on_event(self, event_type, data):
        # print(f"Event: {event_type}, Data: {data}")

        if event_type == "endpoint":
            self.post_endpoint = self.base_url + data
            print(f"Endpoint received: {self.post_endpoint}")
            self.initialize_session()
        elif event_type == "message":
            try:
                message = json.loads(data)
                if message.get("id") == 0 and "result" in message:
                    print("Initialization confirmed!")
                    self.session_initialized = True
            except json.JSONDecodeError:
                pass
        elif event_type == "tool_result":
            print(f"Tool Result Received: {data[:100]}...") # Print first 100 chars
            if self.start_time and self.first_strategy_latency is None:
                self.first_strategy_latency = (time.time() - self.start_time) * 1000
                print(f"First strategy received in {self.first_strategy_latency:.0f}ms")

    def initialize_session(self):
        if not self.post_endpoint:
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
        self.send_post(payload)

    def trigger_simulation(self):
        if not self.post_endpoint:
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
        self.start_time = time.time()
        self.send_post(payload)

    def send_post(self, payload):
        try:
            req = urllib.request.Request(
                self.post_endpoint,
                data=json.dumps(payload).encode('utf-8'),
                headers={'Content-Type': 'application/json'}
            )
            with urllib.request.urlopen(req) as response:
                print(f"POST response: {response.status}")
        except urllib.error.URLError as e:
            print(f"POST failed: {e}")

    def listen_sse(self):
        try:
            req = urllib.request.Request(self.sse_url, headers={'Accept': 'text/event-stream'})
            with urllib.request.urlopen(req) as response:
                print(f"Connected to SSE. Status: {response.status}")
                buffer = ""
                while self.running:
                    try:
                        chunk = response.read(1024).decode('utf-8')
                    except Exception:
                        break
                    if not chunk:
                        print("SSE Connection closed by server")
                        break
                    # print(f"Received chunk: {chunk}") # Debug raw data
                    buffer += chunk
                    while "\n\n" in buffer:
                        event_block, buffer = buffer.split("\n\n", 1)
                        self.process_event_block(event_block)
        except Exception as e:
            print(f"SSE Error: {e}")

    def process_event_block(self, block):
        event_type = "message"
        data = ""
        for line in block.splitlines():
            if line.startswith("event: "):
                event_type = line[7:]
            elif line.startswith("data: "):
                data += line[6:]
        
        if data:
            self.on_event(event_type, data)

    def run(self):
        self.running = True
        # Start SSE listener in a separate thread
        t = threading.Thread(target=self.listen_sse)
        t.daemon = True
        t.start()

        # Wait for initialization
        print("Waiting for initialization...")
        start_wait = time.time()
        while not self.session_initialized:
            if time.time() - start_wait > 30:
                print("Timeout waiting for initialization")
                self.running = False
                sys.exit(1)
            time.sleep(0.5)

        # Trigger simulation
        self.trigger_simulation()
        
        # Wait for results
        print("Waiting for strategies...")
        start_sim = time.time()
        
        # We'll listen for up to 180 seconds and break after first strategy
        while time.time() - start_sim < 180:
            time.sleep(1)
            # If we have received at least one strategy, we can break early
            if self.first_strategy_latency is not None:
                break

        self.running = False
        
        # After loop, print latency if captured
        if self.first_strategy_latency is not None:
            print(f"First strategy latency: {self.first_strategy_latency:.0f}ms")

if __name__ == "__main__":
    BASE_URL = "https://monte-carlo-mcp-server-684569726907.us-central1.run.app"
    tester = SimulationTester(BASE_URL)
    tester.run()
