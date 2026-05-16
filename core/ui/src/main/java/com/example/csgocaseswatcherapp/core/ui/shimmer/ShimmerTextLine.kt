package com.example.csgocaseswatcherapp.core.ui.shimmer


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ShimmerTextLine(
    modifier: Modifier = Modifier,
    width: Dp = AppTheme.dimensions.shimmerTextFieldWidth,
    height: Dp = AppTheme.dimensions.shimmerTextFieldHeight,
) {
    ShimmerBox(
        width = width,
        height = height,
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50)
    )
}