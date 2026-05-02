package com.example.csgocaseswatcherapp.core.ui.shimmer


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerTextLine(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp = 16.dp,
) {
    ShimmerBox(
        width = width,
        height = height,
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50)
    )
}