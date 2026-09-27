package com.curiovana.hufreshman.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RoyalBlueLight,
    onPrimary = Color.White,
    primaryContainer = RoyalBlueDark,
    onPrimaryContainer = Color.White,
    secondary = ElectricIndigo,
    onSecondary = Color.White,
    background = Slate900,
    surface = Slate800,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEBF1FF),
    onPrimaryContainer = RoyalBlueDark,
    secondary = ElectricIndigo,
    onSecondary = Color.White,
    background = Color(0xFFF8FAFC),
    surface = Color.White,
    onBackground = Slate900,
    onSurface = Slate900
)

@Composable
fun HuFreshmanTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Keep app visuals consistent across light & dark/night mode as requested
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// ------------------------------------------------------------
// Screen Size & Adaptive Layout Helpers
// ------------------------------------------------------------
enum class WindowWidthSizeClass {
    COMPACT,   // < 360dp (Small / Narrow phones)
    MEDIUM,    // 360dp - 599dp (Standard to Large phones)
    EXPANDED   // >= 600dp (Foldables unfolded, Tablets, Landscape)
}

data class ScreenDimensions(
    val widthDp: Int,
    val heightDp: Int,
    val widthClass: WindowWidthSizeClass,
    val isCompactWidth: Boolean,
    val isLandscape: Boolean
)

@Composable
fun rememberScreenDimensions(): ScreenDimensions {
    val config = androidx.compose.ui.platform.LocalConfiguration.current
    val width = config.screenWidthDp
    val height = config.screenHeightDp
    val widthClass = when {
        width < 360 -> WindowWidthSizeClass.COMPACT
        width < 600 -> WindowWidthSizeClass.MEDIUM
        else -> WindowWidthSizeClass.EXPANDED
    }
    return ScreenDimensions(
        widthDp = width,
        heightDp = height,
        widthClass = widthClass,
        isCompactWidth = width < 360,
        isLandscape = width > height
    )
}