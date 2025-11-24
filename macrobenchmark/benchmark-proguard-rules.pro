# Benchmark ProGuard Rules
# Keep benchmark classes
-keep class com.surfiniaburger.alora.benchmark.** { *; }

# Suppress warnings for annotation processors (compile-time only)
-dontwarn javax.lang.model.element.Modifier
-dontwarn javax.annotation.processing.AbstractProcessor
-dontwarn javax.annotation.processing.ProcessingEnvironment
-dontwarn javax.annotation.processing.RoundEnvironment
-dontwarn javax.annotation.processing.SupportedAnnotationTypes
-dontwarn javax.annotation.processing.SupportedSourceVersion

# Suppress notes for error-prone and javax.lang.model (not used at runtime)
-dontnote javax.lang.model.**
-dontnote com.google.errorprone.**

# Keep benchmark framework classes
-keep class androidx.benchmark.** { *; }
-keep class androidx.test.** { *; }
