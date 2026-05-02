package com.example.csgocaseswatcherapp.core.ui.shimmer

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

fun Modifier.shimmer(
    shape: Shape,
): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateX = transition.animateFloat(
        initialValue = -400f,
        targetValue = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerTranslate"
    )

    val shimmerColors = listOf(
        AppTheme.colors.surface.copy(alpha = 0.45f),
        AppTheme.colors.onSurface.copy(alpha = 0.10f),
        AppTheme.colors.surface.copy(alpha = 0.45f),
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateX.value, 0f),
        end = Offset(translateX.value + 400f, 400f)
    )

    this
        .clip(shape)
        .background(brush)
}