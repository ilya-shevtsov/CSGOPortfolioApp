package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.R
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.entities.DataRowModel
import com.example.csgocaseswatcherapp.core.ui.R as UiR

@Composable
fun CaseDetailsScreen(
    state: CaseDetailsViewState,
) {

    when (state) {
        is CaseDetailsViewState.Loading -> LoadingScreen()
        is CaseDetailsViewState.Content -> CaseDetailsContent(
            state = state,
        )

    }
}

@Composable
fun CaseDetailsContent(
    state: CaseDetailsViewState.Content,
) {

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .padding(
                horizontal = AppTheme.dimensions.paddingL,
                vertical = AppTheme.dimensions.paddingM
            )
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = state.caseName,
            style = AppTheme.typography.m3.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = AppTheme.colors.onBackground
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = state.caseName,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(UiR.drawable.case_placeholder),
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                state.dataRowModelList.forEach { item ->
                    DataRow(
                        label = stringResource(item.labelId),
                        data = item.value
                    )
                }
            }
        }

        HorizontalDivider(thickness = 2.dp, color = AppTheme.colors.onSurface.copy(alpha = 0.20f))

        Text(
            text = state.description,
            style = AppTheme.typography.m3.bodyLarge,
            color = AppTheme.colors.onBackground,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )
        HorizontalDivider(thickness = 2.dp, color = AppTheme.colors.onSurface.copy(alpha = 0.20f))
    }
}

@Composable
private fun DataRow(
    label: String,
    data: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = label,
            style = AppTheme.typography.m3.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = AppTheme.colors.onBackground,
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = data,
            style = AppTheme.typography.m3.bodyMedium,
            color = AppTheme.colors.onBackground,
            maxLines = 1,
            textAlign = TextAlign.Start
        )
    }
}

@PreviewLightDark
@Composable
fun CaseDetailsScreenPreview() {
    AppTheme {
        CaseDetailsScreen(
            state = CaseDetailsViewState.Content(
                dataRowModelList = listOf(
                    DataRowModel(
                        labelId = R.string.case_lowest_price,
                        value = "$7.47"
                    ),
                    DataRowModel(
                        labelId = R.string.case_volume,
                        value = "1,034"
                    ),
                    DataRowModel(
                        labelId = R.string.case_median_price,
                        value = "$7.39"
                    ),
                    DataRowModel(
                        labelId = R.string.case_release_date,
                        value = "08.01.2015"
                    ),
                    DataRowModel(
                        labelId = R.string.case_drop_status,
                        value = "Inactive (Rare)"
                    ),
                ),
                caseName = "Chroma Case",
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case",

                description = "The Chroma Case is a weapon case consisting of 14 community-desgined weapon skins released as part of the January 8, 2015 update. It requires a Chroma Case Key to be opened. The Chroma Case also has six exclusive community created knife finishes: Damascus Steel, Doppler, Marble Fade, Tiger Tooth, Rust Coat, and Ultraviolet. The Spectrum Case and Spectrum 2 Case includes these Chroma finishes on the Huntsman Knife, Butterfly Knife, Falchion Knife, Shadow Daggers and the Bowie Knife. The Prisma Case contains these Chroma finishes on the Navaja Knife, Stiletto Knife, Talon Knife, and the Ursus Knife."

            ),
        )
    }
}


