# Android Macrobenchmarking

This module contains macrobenchmarks for measuring the performance of the Alora Android app.

## What is Macrobenchmarking?

Macrobenchmarks measure end-to-end app performance from a user's perspective. Unlike microbenchmarks (which test individual functions), macrobenchmarks test complete user journeys including:
- App startup time
- Network request latency
- UI rendering performance
- User interaction responsiveness

## Benchmarks

### Startup Benchmarks (`StartupBenchmark.kt`)

Measures app launch performance in three scenarios:

- **`startupCold()`**: Cold start (app not in memory) - worst case
- **`startupWarm()`**: Warm start (process killed, resources cached) - typical
- **`startupHot()`**: Hot start (app in background) - best case
- **`startupColdWithBaselineProfile()`**: Cold start with code pre-compilation

**Expected Results:**
- Cold startup: < 1000ms
- Warm startup: < 500ms
- Hot startup: < 300ms

### Network Latency Benchmarks (`NetworkLatencyBenchmark.kt`)

Measures network communication performance:

- **`sseConnectionLatency()`**: Time to establish SSE connection
- **`jsonRpcInitializationLatency()`**: JSON-RPC initialization round-trip
- **`strategyComputationLatency()`**: End-to-end strategy calculation
- **`endToEndLatency()`**: Time to first strategy displayed in UI

**Expected Results:**
- SSE connection: < 500ms
- JSON-RPC init: < 200ms
- Strategy computation: < 5000ms
- End-to-end: < 6000ms

## Running Benchmarks

### Prerequisites

1. **Physical Device Required**: Benchmarks must run on a physical Android device (API 26+)
2. **USB Debugging Enabled**: Enable Developer Options and USB Debugging
3. **Device Connected**: Verify with `adb devices`

### Run All Benchmarks

```bash
./gradlew :macrobenchmark:connectedBenchmarkAndroidTest
```

### Run Specific Benchmark

```bash
# Startup benchmarks only
./gradlew :macrobenchmark:connectedBenchmarkAndroidTest \
  -P android.testInstrumentationRunnerArguments.class=\
com.surfiniaburger.alora.benchmark.StartupBenchmark

# Network latency benchmarks only
./gradlew :macrobenchmark:connectedBenchmarkAndroidTest \
  -P android.testInstrumentationRunnerArguments.class=\
com.surfiniaburger.alora.benchmark.NetworkLatencyBenchmark
```

### Run Single Test

```bash
./gradlew :macrobenchmark:connectedBenchmarkAndroidTest \
  -P android.testInstrumentationRunnerArguments.class=\
com.surfiniaburger.alora.benchmark.StartupBenchmark#startupCold
```

## Viewing Results

Benchmark results are saved to:
```
macrobenchmark/build/outputs/androidTest-results/connected/benchmark/
```

Key files:
- `benchmarkData.json`: Raw benchmark metrics
- `*.perfetto-trace`: Perfetto trace files (view at https://ui.perfetto.dev)

## Troubleshooting

### "Failed to grant permissions"

**Cause**: Some OEM devices (Infinix, Xiaomi, Oppo, Realme) block automated permission granting.

**Solution 1 - Manual Grant**:
1. Install the app: `./gradlew :app:installBenchmark`
2. Go to Settings > Apps > Alora > Permissions
3. Grant Location and Notifications permissions
4. Run benchmarks

**Solution 2 - Developer Options**:
1. Settings > Developer Options
2. Find "Disable permission monitoring" or "USB Debugging (Security Settings)"
3. Enable it
4. Run benchmarks

**Solution 3 - Use Different Device**:
- Google Pixel devices work best
- Samsung devices usually work
- Android emulators work but give less accurate results

### "No connected devices"

```bash
# Check device connection
adb devices

# If no devices, reconnect USB and authorize on device
```

### "Benchmark variant not found"

```bash
# Sync Gradle
./gradlew --refresh-dependencies

# Clean and rebuild
./gradlew clean :macrobenchmark:assembleBenchmark
```

### "Debuggable app detected"

The benchmark build type should have `isDebuggable = false`. Check `app/build.gradle.kts`.

### Slow benchmark execution

- Close other apps on the device
- Ensure device is not in battery saver mode
- Use a device with good performance (not budget phones)
- Reduce iterations count in benchmark code (default is 10)

## Device Compatibility

### ✅ Recommended Devices
- Google Pixel (any generation)
- Samsung Galaxy S/Note series
- OnePlus devices
- Stock Android devices

### ⚠️ Known Issues
- **Infinix**: Permission granting blocked, requires manual grant
- **Xiaomi/MIUI**: May need "Disable permission monitoring" in Developer Options
- **Oppo/Realme**: Similar permission restrictions
- **Budget devices**: May have inconsistent performance

### 🚫 Not Recommended
- Android emulators (inaccurate results)
- Devices with heavy custom ROMs
- Devices with aggressive battery optimization

## Best Practices

1. **Run on Release-like Build**: Benchmarks use the `benchmark` build type which mirrors release
2. **Multiple Iterations**: Default is 10 iterations for statistical significance
3. **Consistent Environment**: Same device, same network, same battery level
4. **Baseline Comparison**: Run benchmarks before and after changes
5. **Physical Device**: Always use physical devices, not emulators

## CI/CD Integration

To integrate benchmarks into CI/CD:

1. Use Firebase Test Lab or similar cloud device farm
2. Run benchmarks on every PR
3. Compare results against baseline
4. Fail build if performance regresses > 10%

Example GitHub Actions workflow:
```yaml
- name: Run Benchmarks
  run: ./gradlew :macrobenchmark:connectedBenchmarkAndroidTest
  
- name: Upload Results
  uses: actions/upload-artifact@v3
  with:
    name: benchmark-results
    path: macrobenchmark/build/outputs/androidTest-results/
```

## Further Reading

- [Android Macrobenchmark Guide](https://developer.android.com/topic/performance/benchmarking/macrobenchmark-overview)
- [Baseline Profiles](https://developer.android.com/topic/performance/baselineprofiles)
- [Perfetto Trace Viewer](https://ui.perfetto.dev)
