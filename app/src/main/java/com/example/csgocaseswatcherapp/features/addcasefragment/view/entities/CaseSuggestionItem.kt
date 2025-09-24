package com.example.csgocaseswatcherapp.features.addcasefragment.view.entities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcasefragment.data.entities.AddCaseSuggestion

@Composable
fun CaseSuggestionItem(
    suggestion: AddCaseSuggestion,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(AppTheme.dimensions.paddingML),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(suggestion.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.case_placeholder),
            error = painterResource(R.drawable.ic_error),
            contentDescription = suggestion.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = suggestion.name,
            style = MaterialTheme.typography.bodyLarge,
            color = AppTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@PreviewLightDark
@Composable
private fun CaseOverviewItemPreview() {
    AppTheme {
        CaseSuggestionItem(
            suggestion = AddCaseSuggestion(
                name = "Chroma Case",
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
            ),
            onClick = {}
        )
    }
}