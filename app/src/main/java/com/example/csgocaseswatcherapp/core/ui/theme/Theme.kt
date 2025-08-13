package com.example.csgocaseswatcherapp.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColors = lightColorScheme(
    primary = Color(0xFF40BBD6),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF7CEEFF),
    onPrimaryContainer = Color(0xFF008BA5),
    secondary = Color(0xFFE5F7FF),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFFFFFFFF),
    onSecondaryContainer = Color(0xFFB3C4CC),
    background = Color(0xFFE5F7FF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFEDFaff),
    onSurface = Color(0xFF000000),
    error = Color(0xFFFF0000),
    onError = Color(0xFFFFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF7CEEFF),
    onPrimary = Color(0xFF003641),
    primaryContainer = Color(0xFF005B67),
    onPrimaryContainer = Color(0xFFB7F3FF),
    secondary = Color(0xFFB3C4CC),
    onSecondary = Color(0xFF1C2B30),
    secondaryContainer = Color(0xFF33474E),
    onSecondaryContainer = Color(0xFFD7E8EF),
    background = Color(0xFF101417),
    onBackground = Color(0xFFE2E8EA),
    surface = Color(0xFF12171A),
    onSurface = Color(0xFFE2E8EA),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}