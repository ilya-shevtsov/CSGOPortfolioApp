package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioAction
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioViewState

@Composable
fun PortfolioLandscapeSummaryPanel(
    state: PortfolioViewState.Content,
    onAction: (PortfolioAction) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        PortfolioValueHeader(
            totalPortfolioValue = state.totalPortfolioValue,
            modifier = Modifier.fillMaxWidth()
        )

        PortfolioBarChart(
            entries = state.portfolioBartEntryList,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        PortfolioActionRow(
            onAction = onAction,
            compact = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}