# HU Freshman — ProGuard / R8 rules
# Tuned for maximum obfuscation while keeping runtime-critical APIs intact.

# ── Kotlin ───────────────────────────────────────────────────────────────────
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod
-dontwarn kotlin.**
# Do NOT keep kotlin.Metadata wholesale — it prevents class-name obfuscation

# ── Firebase Auth ─────────────────────────────────────────────────────────────
# Keep only the public API surface; let R8 rename internal implementation classes.
-keep class com.google.firebase.auth.FirebaseAuth { *; }
-keep class com.google.firebase.auth.FirebaseUser { *; }
-keep class com.google.firebase.auth.AuthResult { *; }
-dontwarn com.google.firebase.**

# ── Firebase Firestore ────────────────────────────────────────────────────────
-keep class com.google.firebase.firestore.FirebaseFirestore { *; }
-keep class com.google.firebase.firestore.DocumentSnapshot { *; }
-keep class com.google.firebase.firestore.QuerySnapshot { *; }
-keep class com.google.firebase.firestore.CollectionReference { *; }
-keep class com.google.firebase.firestore.DocumentReference { *; }

# ── Firebase Analytics ────────────────────────────────────────────────────────
-keep class com.google.firebase.analytics.FirebaseAnalytics { *; }

# ── Google Play Services (GMS) ────────────────────────────────────────────────
# Only the task API used by Firebase; internal classes can be obfuscated.
-keep class com.google.android.gms.tasks.Task { *; }
-keep class com.google.android.gms.tasks.OnSuccessListener { *; }
-keep class com.google.android.gms.tasks.OnFailureListener { *; }
-dontwarn com.google.android.gms.**

# ── Gson / Firestore data models ──────────────────────────────────────────────
# Keep class names + field names ONLY for data model classes that need Gson/Firestore deserialization.
# AppRepository, CloudinaryUploader, and EthiopianPhoneUtils are intentionally excluded so R8 can fully obfuscate them.
-keep class com.curiovana.hufreshman.data.ExamQuestion { *; }
-keep class com.curiovana.hufreshman.data.UniversityDetails { *; }
-keep class com.curiovana.hufreshman.data.UniversityGuide { *; }
-keep class com.curiovana.hufreshman.data.Announcement { *; }
-keep class com.curiovana.hufreshman.data.Comment { *; }
-keep class com.curiovana.hufreshman.data.CommunityPost { *; }
-keep class com.curiovana.hufreshman.data.UserProfile { *; }
-keep class com.curiovana.hufreshman.data.MemberRegistration { *; }
-keep class com.curiovana.hufreshman.data.ReportItem { *; }
-keep class com.curiovana.hufreshman.data.SubjectCategory { *; }
-keep class com.curiovana.hufreshman.data.ExamPracticeMode { *; }
-keep class com.curiovana.hufreshman.data.LoginResult { *; }
-keep class com.curiovana.hufreshman.data.PaymentAccount { *; }
-keep class com.curiovana.hufreshman.data.PaymentConfig { *; }
-keep class com.curiovana.hufreshman.data.ModelsKt { *; }

# Gson TypeToken anonymous subclasses MUST keep their generic signatures so
# that Gson can resolve List<UniversityGuide> etc. at runtime in release builds.
-keep class * extends com.google.gson.reflect.TypeToken { *; }
-keepattributes Signature

# Gson itself
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# ── Jetpack Compose ───────────────────────────────────────────────────────────
# Compose relies on compiler-generated code; the Compose compiler plugin handles
# the necessary keep rules automatically. Only suppress spurious warnings here.
-dontwarn androidx.compose.runtime.internal.**
-dontwarn androidx.compose.**
# Do NOT use: -keep class androidx.compose.** { *; } — this disables obfuscation for all of Compose

# ── Coil image loader ─────────────────────────────────────────────────────────
-dontwarn coil.**
-keep class coil.ImageLoader { *; }

# ── Android Framework ─────────────────────────────────────────────────────────
# Activities/Applications/Services must keep their names (referenced in manifest).
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# ── Stack-trace readability (use mapping.txt to de-obfuscate in Play Console) ─
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ── Reverse Engineering Protection & Code Compression ────────────────────────
# NOTE: -repackageclasses is intentionally removed.
# It was breaking Gson TypeToken anonymous subclasses (used for university/exam
# JSON parsing) in release builds, causing all lists to deserialize as null.

# Aggressive class and member renaming dictionary
-useuniqueclassmembernames
-dontusemixedcaseclassnames

# Strip source debugging information
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
}

