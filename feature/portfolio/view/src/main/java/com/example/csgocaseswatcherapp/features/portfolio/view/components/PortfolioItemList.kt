package com.example.csgocaseswatcherapp.features.portfolio.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel

@Composable
fun PortfolioItemList(
    items: List<PortfolioItemModel>,
    listState: LazyListState,
    compact: Boolean,
    modifier: Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
        verticalArrangement = Arrangement.spacedBy(
            if (compact) AppTheme.dimensions.paddingM else AppTheme.dimensions.paddingML
        )
    ) {
        items(items = items, key = { it.itemName }) { item ->
            PortfolioItemCard(
                item = item,
                compact = compact
            )
        }
    }
}