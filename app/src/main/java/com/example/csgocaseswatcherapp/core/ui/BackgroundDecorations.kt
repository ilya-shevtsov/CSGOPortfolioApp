package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundDecorations(modifier: Modifier){
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.fillMaxSize()) {
        val r = 80.dp.toPx()
        drawCircle(
            color = primary,
            radius = r,
            center = Offset(r * 0.75f, r * 0.05f),
            alpha = 0.25f
        )
        drawCircle(
            color = primary,
            radius = r,
            center = Offset(r * -0.1f, r * 0.75f),
            alpha = 0.25f
        )
    }
}