package com.example.csgocaseswatcherapp.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val card: Shape = RoundedCornerShape(12.dp),
    val bottomSheet: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    val button: Shape = RoundedCornerShape(8.dp),
    val image: Shape = RoundedCornerShape(16.dp)
)

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }

