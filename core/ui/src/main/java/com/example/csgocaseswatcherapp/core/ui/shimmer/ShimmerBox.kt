package com.example.csgocaseswatcherapp.core.ui.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    shape: Shape = AppTheme.shapes.shimmerDefaultBox
) {
    Box(
        modifier = modifier
            .then(
                if (width != null && height != null) {
                    Modifier.size(width = width, height = height)
                } else {
                    Modifier
                }
            )
            .shimmer(shape = shape)
    )
}