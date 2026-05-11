package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.adaptive.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioActionRow
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioBarChart
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioItemList
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioLandscapeSummaryPanel
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioValueHeader
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioBarEntryModel
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.portfolio.view.shimmer.PortfolioScreenShimmer
import com.github.mikephil.charting.data.BarEntry
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {

    when (state) {
        is PortfolioViewState.Loading -> PortfolioScreenShimmer(deviceConfigurationType = deviceConfigurationType)
        is PortfolioViewState.Error -> ErrorScreen()
        is PortfolioViewState.Content -> {
            PortfolioContent(
                state = state,
                listState = listState,
                onAction = onAction,
                deviceConfigurationType = deviceConfigurationType,
                modifier = modifier
            )
        }
    }
}

@Composable
fun PortfolioContent(
    state: PortfolioViewState.Content,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {

    when (deviceConfigurationType) {
        DeviceConfigurationType.MOBILE_PORTRAIT -> {
            PortfolioPortraitContent(
                state = state,
                onAction = onAction,
                listState = listState,
                modifier = modifier
            )

        }

        DeviceConfigurationType.MOBILE_LANDSCAPE -> {
            PortfolioLandscapeContent(
                state = state,
                onAction = onAction,
                listState = listState,
                modifier = modifier
            )
        }
    }
}

@Composable
fun PortfolioPortraitContent(
    state: PortfolioViewState.Content,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(AppTheme.dimensions.paddingM),
    ) {
        PortfolioValueHeader(
            totalPortfolioValue = state.totalPortfolioValue,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            val chartHeight = (this.maxWidth * 0.55f).coerceIn(
                minimumValue = 160.dp,
                maximumValue = 220.dp
            )

            val barEntries = state.portfolioBartEntryList.map { entry ->
                BarEntry(entry.x, entry.y)
            }

            PortfolioBarChart(
                entries = barEntries,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(chartHeight)
            )
        }

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        PortfolioActionRow(
            onAction = onAction,
            compact = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        PortfolioItemList(
            items = state.portfolioItemModelList,
            listState = listState,
            compact = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun PortfolioLandscapeContent(
    state: PortfolioViewState.Content,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(AppTheme.dimensions.paddingM),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        PortfolioLandscapeSummaryPanel(
            state = state,
            onAction = onAction,
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight()
        )

        PortfolioItemList(
            items = state.portfolioItemModelList,
            listState = listState,
            compact = true,
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight()
        )
    }
}


@PreviewPortraitLandscapeDarkLight
@Composable
private fun PortfolioScreenPreview() {
    PreviewScreenWithTopBar(
        title = "Portfolio",
        canNavigateBack = true
    ) { deviceConfigurationType, paddingValues ->
        PortfolioScreen(
            state = PortfolioViewState.Content(
                portfolioBartEntryList = persistentListOf(
                    PortfolioBarEntryModel(1f, 20f),
                    PortfolioBarEntryModel(2f, 32f),
                    PortfolioBarEntryModel(3f, 44f),
                    PortfolioBarEntryModel(4f, 58f),
                ),
                totalPortfolioValue = 1_690.65,
                portfolioItemModelList = mockPortfolioItems,
                isSortingSheetVisible = false
            ),
            onAction = {},
            listState = rememberLazyListState(),
            deviceConfigurationType = deviceConfigurationType,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

val mockPortfolioItems = persistentListOf(
    // Big positive position
    PortfolioItemModel(
        itemImage = "",
        itemName = "Chroma 3 Case",
        totalValue = 460.00,
        amount = 200,
        price = 2.30,
        profitLoss = 60.00,
        profitLossPercent = 15.00
    ),

    // Single expensive case
    PortfolioItemModel(
        itemImage = "",
        itemName = "eSports 2013 Case",
        totalValue = 72.50,
        amount = 1,
        price = 72.50,
        profitLoss = 66.50,
        profitLossPercent = 1108.33
    ),

    // Negative profit/loss
    PortfolioItemModel(
        itemImage = "",
        itemName = "Revolution Case",
        totalValue = 225.00,
        amount = 75,
        price = 3.00,
        profitLoss = -37.50,
        profitLossPercent = -14.29
    ),

    // Zero profit/loss
    PortfolioItemModel(
        itemImage = "",
        itemName = "Chroma Case",
        totalValue = 96.00,
        amount = 32,
        price = 3.00,
        profitLoss = 0.00,
        profitLossPercent = 0.00
    ),

    // Long name, useful for checking wrapping/maxLines
    PortfolioItemModel(
        itemImage = "",
        itemName = "Dreams & Nightmares Case",
        totalValue = 352.50,
        amount = 150,
        price = 2.35,
        profitLoss = 52.50,
        profitLossPercent = 17.50
    ),

    // Small cheap stack
    PortfolioItemModel(
        itemImage = "",
        itemName = "Snakebite Case",
        totalValue = 84.00,
        amount = 300,
        price = 0.28,
        profitLoss = -21.00,
        profitLossPercent = -20.00
    ),

    // Medium rare case
    PortfolioItemModel(
        itemImage = "",
        itemName = "Operation Bravo Case",
        totalValue = 400.65,
        amount = 3,
        price = 133.55,
        profitLoss = 340.65,
        profitLossPercent = 567.75
    )
)




