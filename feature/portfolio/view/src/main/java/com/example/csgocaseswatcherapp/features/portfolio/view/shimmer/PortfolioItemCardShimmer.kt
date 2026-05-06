package com.example.csgocaseswatcherapp.features.portfolio.view.shimmer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerBox
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerCard
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun PortfolioItemCardShimmer(
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {

    val imageHeight = if (compact) {
        AppTheme.dimensions.imageCompactNarrowHeight
    } else {
        AppTheme.dimensions.imageNarrowHeight
    }


    val cardPadding = if (compact) {
        AppTheme.dimensions.paddingM
    } else {
        AppTheme.dimensions.paddingML
    }

    ShimmerCard(
        modifier = modifier
            .fillMaxWidth()
            .height(imageHeight + cardPadding * 2),
        shape = AppTheme.shapes.narrowCard
    ) {
        ShimmerBox(
            modifier = Modifier
                .fillMaxSize(),
            shape = AppTheme.shapes.narrowCard
        )
    }
}