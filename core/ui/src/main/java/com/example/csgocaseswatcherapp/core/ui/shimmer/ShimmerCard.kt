package com.example.csgocaseswatcherapp.core.ui.shimmer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun ShimmerCard(
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.cardDefault,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation),
        shape = shape,
        content = content
    )
}