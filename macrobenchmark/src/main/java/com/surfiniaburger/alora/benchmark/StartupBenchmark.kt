package com.surfiniaburger.alora.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
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
 * Startup benchmark for measuring app launch performance.
 *
 * This benchmark measures three types of startup:
 * 1. **Cold Startup**: App process doesn't exist, no cached data
 * 2. **Warm Startup**: App process killed but some system resources cached
 * 3. **Hot Startup**: App process exists in background, brought to foreground
 *
 * Metrics measured:
 * - Time To Initial Display (TTID): Time until first frame is drawn
 * - Time To Fully Drawn (TTFD): Time until app reports it's fully loaded
 * - Frame timing during startup animation
 */
@RunWith(AndroidJUnit4::class)
class StartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    /**
     * Cold startup benchmark - measures worst-case startup time.
     * 
     * Cold startup happens when:
     * - User hasn't opened the app recently
     * - System killed the app to reclaim memory
     * - Device just rebooted
     *
     * This is the most important startup metric as it represents
     * the first impression users get of your app.
     */
    @Test
    fun startupCold() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(
            StartupTimingMetric(),
            FrameTimingMetric()
        ),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        // Wait for the app to be fully loaded
        // Adjust this based on your app's UI elements
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }

    /**
     * Warm startup benchmark - measures typical restart performance.
     *
     * Warm startup happens when:
     * - User recently used the app
     * - System killed the process but kept some resources
     * - User switches back to the app
     *
     * This represents a common scenario where the user multitasks
     * and returns to your app.
     */
    @Test
    fun startupWarm() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(
            StartupTimingMetric(),
            FrameTimingMetric()
        ),
        iterations = 10,
        startupMode = StartupMode.WARM,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }

    /**
     * Hot startup benchmark - measures resume performance.
     *
     * Hot startup happens when:
     * - App is already running in background
     * - User switches back to the app
     * - No process recreation needed
     *
     * This is the fastest startup type and represents the best-case
     * scenario for app resume.
     */
    @Test
    fun startupHot() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(
            StartupTimingMetric(),
            FrameTimingMetric()
        ),
        iterations = 10,
        startupMode = StartupMode.HOT,
        compilationMode = CompilationMode.DEFAULT
    ) {
        pressHome()
        startActivityAndWait()
        
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }

    /**
     * Baseline profile comparison - measures startup with compilation.
     *
     * This benchmark uses PARTIAL compilation mode
     * to measure the impact of baseline profiles on startup performance.
     *
     * Baseline profiles pre-compile critical code paths, significantly
     * improving startup time.
     */
    @Test
    fun startupColdWithBaselineProfile() = benchmarkRule.measureRepeated(
        packageName = "com.surfiniaburger.alora",
        metrics = listOf(
            StartupTimingMetric(),
            FrameTimingMetric()
        ),
        iterations = 10,
        startupMode = StartupMode.COLD,
        compilationMode = CompilationMode.Partial()
    ) {
        pressHome()
        startActivityAndWait()
        
        device.wait(Until.hasObject(By.pkg(packageName)), 10_000)
    }
}
