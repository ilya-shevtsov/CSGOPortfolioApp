package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CaseImage(
    imageUrl: String,
    size: Dp,
    clipShape: Shape,
    modifier: Modifier = Modifier
) {
    CaseImage(
        imageUrl = imageUrl,
        width = size,
        height = size,
        clipShape = clipShape,
        modifier = modifier
    )
}

@Composable
fun CaseImage(
    imageUrl: String,
    width: Dp,
    height: Dp,
    clipShape: Shape,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        error = painterResource(R.drawable.ic_error),
        placeholder = painterResource(R.drawable.case_placeholder),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(width = width, height = height)
            .clip(clipShape)
    )
}