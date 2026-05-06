package com.example.csgocaseswatcherapp.features.addcase.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.CaseImage
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion

@Composable
fun CaseSuggestionItem(
    suggestion: AddCaseSuggestion,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.cardDefault)
            .clickable(onClick = onClick)
            .padding(AppTheme.dimensions.paddingML),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CaseImage(
            imageUrl = suggestion.imageUrl,
            size = AppTheme.dimensions.imageXSSize,
            clipShape = AppTheme.shapes.imageClip
        )

        Spacer(Modifier.width(AppTheme.dimensions.paddingML))

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