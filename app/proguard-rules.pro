# HU Freshman — ProGuard / R8 rules

# ── Kotlin ──────────────────────────────────────────────────────────────────
-keepattributes *Annotation*, InnerClasses, Signature, EnclosingMethod
-dontwarn kotlin.**
-keep class kotlin.Metadata { *; }

# ── Firebase Firestore / Auth ────────────────────────────────────────────────
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# ── Gson / data models (serialized to/from Firestore) ───────────────────────
-keepclassmembers class com.curiovana.hufreshman.data.** {
    <fields>;
    <init>();
}
-keep class com.curiovana.hufreshman.data.** { *; }

# ── Jetpack Compose ──────────────────────────────────────────────────────────
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }

# ── Coil image loader ────────────────────────────────────────────────────────
-dontwarn coil.**

# ── General Android ──────────────────────────────────────────────────────────
-keepattributes SourceFile,LineNumberTable
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
