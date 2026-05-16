package com.example.csgocaseswatcherapp.features.portfolio.view.shimmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerBox
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerCard
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme

@Composable
fun PortfolioActionRowShimmer(
    compact: Boolean,
    modifier: Modifier = Modifier
) {
    val buttonHeight = if (compact) 40.dp else 44.dp

    ShimmerCard(
        modifier = modifier.fillMaxWidth(),
        shape = AppTheme.shapes.cardDefault
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            repeat(3) {
                ShimmerBox(
                    modifier = Modifier
                        .weight(1f)
                        .height(buttonHeight),
                    shape = AppTheme.shapes.buttonRounded
                )
            }
        }
    }
}