package com.example.csgocaseswatcherapp.core.ui

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CaseImage(
    context: Context,
    caseName: String,
    imageUrl: String,
    size: Dp,
    clipShape: Shape,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = caseName,
        error = painterResource(R.drawable.ic_error),
        placeholder = painterResource(R.drawable.case_placeholder),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size)
            .clip(clipShape)
    )
}