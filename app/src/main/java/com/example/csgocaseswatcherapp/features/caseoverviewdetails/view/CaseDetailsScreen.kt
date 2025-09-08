package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun CaseDetailsScreen(
    state: CaseDetailsViewState,
    onAction: (CaseDetailsViewAction) -> Unit
) {

    when (state) {
        is CaseDetailsViewState.Loading -> LoadingScreen()
        is CaseDetailsViewState.Content -> CaseDetailsContent(
            state = state,
            onAction = onAction
        )

    }
}

@Composable
fun CaseDetailsContent(
    state: CaseDetailsViewState.Content,
    onAction: (CaseDetailsViewAction) -> Unit
) {

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
                placeholder = painterResource(R.drawable.case_placeholder),
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
                DataRow(
                    label = stringResource(R.string.case_lowest_price),
                    data = state.lowestPrice
                )
                DataRow(
                    label = stringResource(R.string.case_volume),
                    data = state.volume
                )
                DataRow(
                    label = stringResource(R.string.case_median_price),
                    data = state.medianPrice
                )
                DataRow(
                    label = stringResource(R.string.case_release_date),
                    data = state.releaseDate
                )
                DataRow(
                    label = stringResource(R.string.case_drop_status),
                    data = state.dropStatus
                )
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

@Composable
@Preview
fun CaseDetailsScreenPreview() {
    AppTheme(darkTheme = false) {
        CaseDetailsScreen(
            state = CaseDetailsViewState.Content(

                caseName = "Chroma Case",
                lowestPrice = "$7.47",
                volume = "1,034",
                medianPrice = "$7.39",
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case",
                releaseDate = "08.01.2015",
                dropStatus = "Inactive (Rare)",
                description = "The Chroma Case is a weapon case consisting of 14 community-desgined weapon skins released as part of the January 8, 2015 update. It requires a Chroma Case Key to be opened. The Chroma Case also has six exclusive community created knife finishes: Damascus Steel, Doppler, Marble Fade, Tiger Tooth, Rust Coat, and Ultraviolet. The Spectrum Case and Spectrum 2 Case includes these Chroma finishes on the Huntsman Knife, Butterfly Knife, Falchion Knife, Shadow Daggers and the Bowie Knife. The Prisma Case contains these Chroma finishes on the Navaja Knife, Stiletto Knife, Talon Knife, and the Ursus Knife."

            ),
            onAction = {}
        )
    }
}

@Composable
@Preview
fun CaseDetailsScreenPreviewDark() {
    AppTheme(darkTheme = true) {
        CaseDetailsScreen(
            state = CaseDetailsViewState.Content(
                caseName = "Chroma Case",
                lowestPrice = "$7.47",
                volume = "1,034",
                medianPrice = "$7.39",
                imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case",
                releaseDate = "08.01.2015",
                dropStatus = "Inactive (Rare)",
                description = "The Chroma Case is a weapon case consisting of 14 community-desgined weapon skins released as part of the January 8, 2015 update. It requires a Chroma Case Key to be opened. The Chroma Case also has six exclusive community created knife finishes: Damascus Steel, Doppler, Marble Fade, Tiger Tooth, Rust Coat, and Ultraviolet. The Spectrum Case and Spectrum 2 Case includes these Chroma finishes on the Huntsman Knife, Butterfly Knife, Falchion Knife, Shadow Daggers and the Bowie Knife. The Prisma Case contains these Chroma finishes on the Navaja Knife, Stiletto Knife, Talon Knife, and the Ursus Knife."

            ),
            onAction = {}
        )
    }
}


