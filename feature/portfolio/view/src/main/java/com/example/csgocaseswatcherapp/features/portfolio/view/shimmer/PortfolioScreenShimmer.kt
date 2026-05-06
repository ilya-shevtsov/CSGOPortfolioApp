package com.example.csgocaseswatcherapp.features.portfolio.view.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDark
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerBox
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerTextLine
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun PortfolioScreenShimmer(modifier: Modifier = Modifier, deviceConfigurationType: DeviceConfigurationType) {
    when (deviceConfigurationType) {
        DeviceConfigurationType.MOBILE_PORTRAIT -> {
            PortfolioPortraitShimmer(modifier = modifier)
        }

        DeviceConfigurationType.MOBILE_LANDSCAPE -> {
            PortfolioLandscapeShimmer(modifier = modifier)
        }
    }
}

@Composable
private fun PortfolioPortraitShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(AppTheme.dimensions.paddingM),
    ) {
        ShimmerTextLine(width = AppTheme.dimensions.shimmerTextFieldTitleWidth)

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            val chartHeight = (this.maxWidth * 0.55f).coerceIn(
                minimumValue = 160.dp,
                maximumValue = 220.dp
            )

            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(chartHeight), shape = AppTheme.shapes.cardDefault
            )
        }

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        PortfolioActionRowShimmer(
            compact = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        LazyColumn(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight(),
            contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingML)
        ) {
            items(6) {
                PortfolioItemCardShimmer(compact = false)
            }
        }
    }
}


@Composable
private fun PortfolioLandscapeShimmer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(AppTheme.dimensions.paddingM),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        Column(
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            ShimmerTextLine(width = AppTheme.dimensions.shimmerTextFieldTitleWidth)
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), shape = AppTheme.shapes.cardDefault
            )

            PortfolioActionRowShimmer(
                compact = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight(),
            contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            items(6) {
                PortfolioItemCardShimmer(compact = true)
            }
        }
    }
}

@PreviewPortraitLandscapeDark
@Composable
private fun PortfolioScreenShimmerPreview() {
    PreviewScreenWithTopBar(
        title = "Portfolio",
        canNavigateBack = true
    ) { deviceConfigurationType, paddingValues ->
        PortfolioScreenShimmer(
            modifier = Modifier.padding(paddingValues),
            deviceConfigurationType = deviceConfigurationType
        )
    }
}