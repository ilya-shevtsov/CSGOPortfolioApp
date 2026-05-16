package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioAction

@Composable
fun PortfolioActionRow(
    onAction: (PortfolioAction) -> Unit,
    compact: Boolean,
    modifier: Modifier
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        shape = AppTheme.shapes.cardDefault
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            PortfolioButton(
                modifier = Modifier.weight(1f),
                onClick = { onAction(PortfolioAction.OnPortfolioDetailsClicked) },
                icon = Icons.AutoMirrored.Filled.List,
                text = stringResource(R.string.details_button),
                compact = compact
            )

            PortfolioButton(
                modifier = Modifier.weight(1f),
                onClick = { onAction(PortfolioAction.OnSortClicked) },
                icon = Icons.AutoMirrored.Filled.Sort,
                text = stringResource(R.string.sorting_button),
                compact = compact

            )
            PortfolioButton(
                modifier = Modifier.weight(1f),
                onClick = { onAction(PortfolioAction.OnAddCaseClicked) },
                icon = Icons.Default.Add,
                text = stringResource(R.string.add_case_button),
                compact = compact
            )
        }
    }
}