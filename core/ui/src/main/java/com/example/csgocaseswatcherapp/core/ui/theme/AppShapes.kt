package com.example.csgocaseswatcherapp.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val bottomSheet: Shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),

    val buttonNormal: Shape = RoundedCornerShape(8.dp),
    val buttonRounded: Shape = RoundedCornerShape(16.dp),

    val imageClip: Shape = RoundedCornerShape(16.dp),

    val cardDefault: Shape = RoundedCornerShape(16.dp),

    val narrowCard: Shape = RoundedCornerShape(20.dp),

    val statTile: Shape = RoundedCornerShape(16.dp),

    val pill: Shape = RoundedCornerShape(100.dp),

    val shimmerDefaultBox: Shape = RoundedCornerShape(8.dp)
)

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }

