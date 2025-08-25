package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun CaseOverviewItem(
    item: CaseOverviewModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.caseName,
                placeholder = painterResource(R.drawable.d_chroma_case),
                modifier = Modifier.size(height = 100.dp, width = 100.dp),
                alignment = Alignment.Center
            )
        }
    }
}


@Preview
@Composable
fun CaseOverviewItemPreview() {
    AppTheme {
        CaseOverviewItem(
            item = CaseOverviewModel(
                caseName = "Recoil Case",
                lowestPrice = 0.46,
                volume = 99546,
                medianPrice = 0.45,
                imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case",
                releaseDate = "01.07.2022",
                dropStatus = "Active",
                description = "The Recoil Case..."
            ),
            onClick = {}
        )
    }
}