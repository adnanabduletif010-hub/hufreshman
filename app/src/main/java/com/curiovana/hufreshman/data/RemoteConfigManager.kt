package com.curiovana.hufreshman.data

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppUpdateInfo(
    val isUpdateRequired: Boolean = false,
    val currentVersionCode: Long = 0L,
    val currentVersionName: String = "",
    val minRequiredVersionCode: Long = 0L,
    val latestVersionName: String = "",
    val updateTitle: String = "Update Required",
    val updateMessage: String = "A new version of HU Freshman is available. Please update to continue using the app.",
    val updateUrl: String = "https://play.google.com/store/apps/details?id=com.curiovana.hufreshman"
)

object RemoteConfigManager {
    private const val TAG = "RemoteConfigManager"

    // Remote Config Keys
    const val KEY_MIN_REQUIRED_VERSION = "min_required_version_code"
    const val KEY_LATEST_VERSION_CODE = "latest_version_code"
    const val KEY_LATEST_VERSION_NAME = "latest_version_name"
    const val KEY_FORCE_UPDATE_ENABLED = "force_update_enabled"
    const val KEY_UPDATE_TITLE = "force_update_title"
    const val KEY_UPDATE_MESSAGE = "force_update_message"
    const val KEY_UPDATE_URL = "update_url"

    private val _updateInfo = MutableStateFlow(AppUpdateInfo())
    val updateInfo: StateFlow<AppUpdateInfo> = _updateInfo.asStateFlow()

    fun init(context: Context) {
        val appContext = context.applicationContext
        val currentVersionCode = getAppVersionCode(appContext)
        val currentVersionName = getAppVersionName(appContext)

        _updateInfo.value = _updateInfo.value.copy(
            currentVersionCode = currentVersionCode,
            currentVersionName = currentVersionName
        )

        try {
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                // Check frequently (0 seconds interval ensures immediate fetch on every app startup)
                minimumFetchIntervalInSeconds = 0
            }
            remoteConfig.setConfigSettingsAsync(configSettings)

            // Safe in-app defaults matching current app release
            val defaultUrl = "https://play.google.com/store/apps/details?id=${appContext.packageName}"
            val defaults: Map<String, Any> = mapOf(
                KEY_MIN_REQUIRED_VERSION to currentVersionCode,
                KEY_LATEST_VERSION_CODE to currentVersionCode,
                KEY_LATEST_VERSION_NAME to currentVersionName,
                KEY_FORCE_UPDATE_ENABLED to false,
                KEY_UPDATE_TITLE to "Update Required",
                KEY_UPDATE_MESSAGE to "A new version of HU Freshman is available with critical improvements. Please update to the latest version to continue.",
                KEY_UPDATE_URL to defaultUrl
            )
            remoteConfig.setDefaultsAsync(defaults)

            // Initial fetch & activate
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Remote config fetch and activate successful.")
                    } else {
                        Log.w(TAG, "Remote config fetch failed: ${task.exception?.message}")
                    }
                    evaluateUpdateRequirements(appContext, remoteConfig)
                }

            // Real-time listener for instant live updates while user has the app open
            remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    Log.d(TAG, "Remote Config updated keys: ${configUpdate.updatedKeys}")
                    remoteConfig.activate().addOnCompleteListener {
                        evaluateUpdateRequirements(appContext, remoteConfig)
                    }
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    Log.w(TAG, "Remote Config listener error: ${error.message}")
                }
            })
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Firebase Remote Config", e)
        }
    }

    fun checkUpdate(context: Context) {
        val appContext = context.applicationContext
        try {
            val remoteConfig = Firebase.remoteConfig
            remoteConfig.fetchAndActivate().addOnCompleteListener {
                evaluateUpdateRequirements(appContext, remoteConfig)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking update", e)
        }
    }

    private fun evaluateUpdateRequirements(context: Context, remoteConfig: FirebaseRemoteConfig) {
        val currentVersionCode = getAppVersionCode(context)
        val currentVersionName = getAppVersionName(context)

        val minRequiredVersion = remoteConfig.getLong(KEY_MIN_REQUIRED_VERSION)
        val latestVersionCode = remoteConfig.getLong(KEY_LATEST_VERSION_CODE)
        val forceUpdateEnabled = remoteConfig.getBoolean(KEY_FORCE_UPDATE_ENABLED)
        val latestVersionName = remoteConfig.getString(KEY_LATEST_VERSION_NAME)
        val title = remoteConfig.getString(KEY_UPDATE_TITLE).ifBlank { "Update Required" }
        val message = remoteConfig.getString(KEY_UPDATE_MESSAGE).ifBlank {
            "A new version of HU Freshman is available. Please update to continue using the app."
        }
        val url = remoteConfig.getString(KEY_UPDATE_URL).ifBlank {
            "https://play.google.com/store/apps/details?id=${context.packageName}"
        }

        // Force update is triggered if:
        // 1. Current version code is strictly lower than min_required_version_code, OR
        // 2. force_update_enabled is true AND (latest_version_code > currentVersionCode OR min_required_version_code > currentVersionCode)
        val isUpdateRequired = (minRequiredVersion > currentVersionCode) ||
                (forceUpdateEnabled && (latestVersionCode > currentVersionCode || minRequiredVersion > currentVersionCode))

        _updateInfo.value = AppUpdateInfo(
            isUpdateRequired = isUpdateRequired,
            currentVersionCode = currentVersionCode,
            currentVersionName = currentVersionName,
            minRequiredVersionCode = if (minRequiredVersion > 0) minRequiredVersion else latestVersionCode,
            latestVersionName = latestVersionName,
            updateTitle = title,
            updateMessage = message,
            updateUrl = url
        )

        Log.d(TAG, "Update evaluation: required=$isUpdateRequired, currentCode=$currentVersionCode, minCode=$minRequiredVersion, forceFlag=$forceUpdateEnabled")
    }

    fun getAppVersionCode(context: Context): Long {
        return try {
            val pInfo: PackageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    android.content.pm.PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pInfo.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                pInfo.versionCode.toLong()
            }
        } catch (e: Exception) {
            20L // default fallback to current version code
        }
    }

    fun getAppVersionName(context: Context): String {
        return try {
            val pInfo: PackageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    android.content.pm.PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(context.packageName, 0)
            }
            pInfo.versionName ?: "3.9"
        } catch (e: Exception) {
            "3.9"
        }
    }
}
