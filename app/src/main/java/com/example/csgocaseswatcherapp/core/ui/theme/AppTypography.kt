package com.example.csgocaseswatcherapp.core.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf

data class AppTypography(
    val m3: Typography = Typography()
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }

