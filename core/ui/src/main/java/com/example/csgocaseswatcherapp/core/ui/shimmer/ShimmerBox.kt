package com.example.csgocaseswatcherapp.core.ui.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ShimmerBox(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.shimmerDefaultBox
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .shimmer(shape)
    )
}