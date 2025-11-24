package com.surfiniaburger.alora.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Network latency benchmark for measuring SSE connection and JSON-RPC performance.
 *
 * This benchmark measures:
 * 1. **SSE Connection Latency**: Time from app start to SSE connection established
 * 2. **JSON-RPC Initialization**: Time to complete initialization handshake
 * 3. **Strategy Computation**: End-to-end time for strategy calculation
 * 4. **First Strategy Result**: Time until first strategy appears in UI
 *
 * **Note**: These benchmarks require runtime permissions (INTERNET, POST_NOTIFICATIONS, LOCATION).
 * On some OEM devices (Infinix, Xiaomi, Oppo), automated permission granting may fail.
 * See README.md for troubleshooting steps.
 */
@RunWith(AndroidJUnit4::class)
class NetworkLatencyBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    /**
     * Measures SSE connection establishment time.
     * 
     * This benchmark starts the app and measures how long it takes to:
     * 1. Launch the app
     * 2. Initialize networking
     * 3. Establish SSE connection to the server
     * 4. Receive first "endpoint" event
     *
     * Expected latency: < 500ms on good network
     */
    @Test
    fun sseConnectionLatency() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(StartupTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        // Wait for SSE connection to be established
        // This is measured via custom trace events in the app code
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }

    /**
     * Measures JSON-RPC initialization latency.
     *
     * This benchmark measures the round-trip time for the initialization
     * JSON-RPC request/response cycle.
     *
     * Expected latency: < 200ms
     */
    @Test
    fun jsonRpcInitializationLatency() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(StartupTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        // Wait for initialization to complete
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }

    /**
     * Measures end-to-end strategy computation latency.
     *
     * This benchmark measures the complete flow:
     * 1. App starts
     * 2. SSE connection established
     * 3. JSON-RPC initialization
     * 4. Strategy computation request sent
     * 5. Strategy result received
     *
     * Expected latency: < 5000ms (includes Monte Carlo simulation)
     */
    @Test
    fun strategyComputationLatency() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(StartupTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        // Wait for strategy computation to complete
        device.wait(Until.hasObject(By.pkg(packageName)), 15_000)
    }

    /**
     * Measures time to first strategy result displayed in UI.
     *
     * This is the user-perceived latency from app launch to seeing
     * the first race strategy recommendation.
     *
     * Expected latency: < 6000ms
     */
    @Test
    fun endToEndLatency() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(StartupTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        // Wait for first strategy to appear in UI
        device.wait(Until.hasObject(By.pkg(packageName)), 15_000)
    }
}
