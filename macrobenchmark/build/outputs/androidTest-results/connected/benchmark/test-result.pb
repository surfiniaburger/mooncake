
ı5
y
StartupBenchmark"com.surfiniaburger.alora.benchmark
startupHot2˝Áë…¿∫ô:˝Áë…ÄôõB
11220643C1004927primaryÖ/
±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
java.lang.SecurityException±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
"·

logcatandroidÀ
»/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/logcat-com.surfiniaburger.alora.benchmark.StartupBenchmark-startupHot.txt"´

device-infoandroidê
ç/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/device-info.pb"¨

device-info.meminfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/meminfo"¨

device-info.cpuinfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/cpuinfo˜5
z
StartupBenchmark"com.surfiniaburger.alora.benchmarkstartupCold2˝Áë…ÄΩÉ:˝Áë…Ä∆˝B
11220643C1004927primaryÖ/
±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
java.lang.SecurityException±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
"‚

logcatandroidÃ
…/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/logcat-com.surfiniaburger.alora.benchmark.StartupBenchmark-startupCold.txt"´

device-infoandroidê
ç/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/device-info.pb"¨

device-info.meminfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/meminfo"¨

device-info.cpuinfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/cpuinfo˜5
z
StartupBenchmark"com.surfiniaburger.alora.benchmarkstartupWarm2˝Áë…Äœ˜:˝Áë…ÄÿÒB
11220643C1004927primaryÖ/
±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
java.lang.SecurityException±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
"‚

logcatandroidÃ
…/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/logcat-com.surfiniaburger.alora.benchmark.StartupBenchmark-startupWarm.txt"´

device-infoandroidê
ç/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/device-info.pb"¨

device-info.meminfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/meminfo"¨

device-info.cpuinfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/cpuinfoû6
ç
StartupBenchmark"com.surfiniaburger.alora.benchmarkstartupColdWithBaselineProfile2˝Áë…¿‹Æ:˝Áë…¿Â®B
11220643C1004927primaryÖ/
±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
java.lang.SecurityException±java.lang.SecurityException: Error granting runtime permission
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1310)
at android.app.UiAutomation.grantRuntimePermission(UiAutomation.java:1277)
at androidx.test.runner.permission.UiAutomationPermissionGranter.requestPermissions(UiAutomationPermissionGranter.java:44)
at androidx.test.rule.GrantPermissionRule$RequestPermissionStatement.evaluate(GrantPermissionRule.java:149)
at org.junit.rules.RunRules.evaluate(RunRules.java:20)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2335)
Caused by: java.lang.SecurityException: Package com.surfiniaburger.alora has not requested permission android.permission.WRITE_EXTERNAL_STORAGE
at android.os.Parcel.createExceptionOrNull(Parcel.java:3039)
at android.os.Parcel.createException(Parcel.java:3023)
at android.os.Parcel.readException(Parcel.java:3006)
at android.os.Parcel.readException(Parcel.java:2948)
at android.app.IUiAutomationConnection$Stub$Proxy.grantRuntimePermission(IUiAutomationConnection.java:646)
at android.app.UiAutomation.grantRuntimePermissionAsUser(UiAutomation.java:1307)
... 32 more
"ı

logcatandroidﬂ
‹/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/logcat-com.surfiniaburger.alora.benchmark.StartupBenchmark-startupColdWithBaselineProfile.txt"´

device-infoandroidê
ç/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/device-info.pb"¨

device-info.meminfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/meminfo"¨

device-info.cpuinfoandroidâ
Ü/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/cpuinfo" *ê
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriverö
ó/Users/surfiniaburger/Desktop/mooncake/macrobenchmark/build/outputs/androidTest-results/connected/benchmark/Infinix X6528 - 13/testlog/test-results.log 2
text/plain