import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { load(it) }
    }
}

android {
    namespace = "com.curiovana.hufreshman"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.curiovana.hufreshman"
        minSdk = 24
        targetSdk = 37
        versionCode = 9
        versionName = "2.9"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val storeProp = localProperties.getProperty("RELEASE_STORE_FILE")
            val keystoreFile = when {
                storeProp != null && rootProject.fil e(storeProp.replace("../", "")).exists() ->
                    rootProject.file(storeProp.replace("../", ""))
                file("release.keystore").exists() -> file("release.keystore")
                rootProject.file("app/release.keystore").exists() -> rootProject.file("app/release.keystore")
                else -> file("release.keystore")
            }
            storeFile = keystoreFile
            storePassword = localProperties.getProperty("RELEASE_STORE_PASSWORD") ?: "42434342"
            keyAlias = localProperties.getProperty("RELEASE_KEY_ALIAS") ?: "key0"
            keyPassword = localProperties.getProperty("RELEASE_KEY_PASSWORD") ?: "42434342"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            optimization {
                enable = false
            }
        }
        debug {
            // Use release signing key so debug builds match Play Store closed-test signatures
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Firebase BoM & SDKs
    implementation(platform("com.google.firebase:firebase-bom:34.19.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.google.gson)
    implementation(libs.coil.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}