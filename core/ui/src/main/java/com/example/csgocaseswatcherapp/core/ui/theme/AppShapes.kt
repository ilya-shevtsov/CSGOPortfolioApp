package com.example.csgocaseswatcherapp.core.ui.theme

import androidx.compose.material3.Shapes
import androidx.compose.runtime.staticCompositionLocalOf

data class AppShapes(
    val m3: Shapes = Shapes()
)

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }

