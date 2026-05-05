package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.CaseImage
import com.example.csgocaseswatcherapp.core.ui.StatRow
import com.example.csgocaseswatcherapp.core.ui.StatRowShimmer
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerBox
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerCard
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerTextLine
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun CaseOverviewItem(
    item: CaseOverviewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = AppTheme.shapes.card,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CaseImage(
                imageUrl = item.imageUrl,
                size = AppTheme.dimensions.imageMediumSize,
                clipShape = AppTheme.shapes.imageClip
            )

            Spacer(Modifier.width(AppTheme.dimensions.paddingM))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
            ) {
                Text(
                    text = item.caseName,
                    style = MaterialTheme.typography.titleMedium,
                    color = AppTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                StatRow(
                    icon = Icons.Outlined.Sell,
                    label = "Lowest Price",
                    value = item.lowestPrice.toString(),
                )
                StatRow(
                    icon = Icons.Outlined.Inventory2,
                    label = "Volume",
                    value = item.volume.toString()
                )
                StatRow(
                    icon = Icons.Outlined.Leaderboard,
                    label = "Median Price",
                    value = item.medianPrice.toString()
                )
            }
        }
    }
}

@Composable
fun CaseOverviewItemShimmer() {
    ShimmerCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerBox(
                width = AppTheme.dimensions.imageMediumSize,
                height = AppTheme.dimensions.imageMediumSize,
                shape = AppTheme.shapes.imageClip
            )

            Spacer(Modifier.width(AppTheme.dimensions.paddingM))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
            ) {
                ShimmerTextLine(width = AppTheme.dimensions.shimmerTextFieldTitleWidth)
                StatRowShimmer()
                StatRowShimmer()
                StatRowShimmer()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CaseOverviewItemShimmerPreview() {
    AppTheme {
        CaseOverviewItemShimmer()
    }
}

@PreviewLightDark
@Composable
private fun CaseOverviewItemPreview() {
    AppTheme {
        CaseOverviewItem(
            item = CaseOverviewModel(
                caseName = "Chroma Case",
                lowestPrice = 6.63,
                volume = 1013,
                medianPrice = 7.45,
                imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case",
                releaseDate = "01.07.2022",
                dropStatus = "Active",
                description = "—"
            ),
            onClick = {}
        )
    }
}
