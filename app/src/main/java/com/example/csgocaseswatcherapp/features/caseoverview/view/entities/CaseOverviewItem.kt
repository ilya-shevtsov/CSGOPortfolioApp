package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme


@Composable
fun CaseOverviewItem(
    item: CaseOverviewModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingML)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.caseName,
                placeholder = painterResource(R.drawable.case_placeholder),
                error = painterResource(R.drawable.ic_error),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
private fun StatRow(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppTheme.colors.primary,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colors.onSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colors.onSurface
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CaseOverviewItemPreview() {
    AppTheme(darkTheme = true) {
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
