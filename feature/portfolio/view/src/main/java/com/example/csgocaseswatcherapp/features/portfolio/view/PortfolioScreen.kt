package com.example.csgocaseswatcherapp.features.portfolio.view

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDark
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerBox
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerCard
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerTextLine
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioActionRow
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioBarChart
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioItemCard
import com.example.csgocaseswatcherapp.features.portfolio.view.components.PortfolioValueHeader
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.portfolio.view.shimmer.PortfolioItemCardShimmer
import com.github.mikephil.charting.data.BarEntry


@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {

    when (state) {
        is PortfolioViewState.Loading -> LoadingScreen()
        is PortfolioViewState.Error -> PortfolioScreenShimmer(deviceConfigurationType = deviceConfigurationType)
        is PortfolioViewState.Content -> PortfolioContent(
            state = state,
            listState = listState,
            onAction = onAction,
            deviceConfigurationType = deviceConfigurationType,
            modifier = modifier
        )
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

            PortfolioBarChart(
                entries = state.portfolioBartEntryList,
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
fun PortfolioLandscapeContent(
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

@Composable
fun PortfolioScreenShimmer(modifier: Modifier = Modifier, deviceConfigurationType: DeviceConfigurationType) {
    when (deviceConfigurationType) {
        DeviceConfigurationType.MOBILE_PORTRAIT -> {
            LoadingScreen()
        }

        DeviceConfigurationType.MOBILE_LANDSCAPE -> {
            PortfolioLandscapeShimmer(modifier = modifier)
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

            ShimmerCard(modifier = Modifier.fillMaxWidth()) {
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
                                .height(40.dp),
                            shape = AppTheme.shapes.buttonRounded
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight(),
            contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
        ) {
            items(6) {
                PortfolioItemCardShimmer()
            }
        }
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
                portfolioBartEntryList = listOf(
                    BarEntry(1f, 20f),
                    BarEntry(2f, 32f),
                    BarEntry(3f, 44f),
                    BarEntry(4f, 58f),
                ),
                totalPortfolioValue = 1_690.65,
                portfolioItemModelList = mockPortfolioItems
            ),
            onAction = {},
            listState = rememberLazyListState(),
            deviceConfigurationType = deviceConfigurationType,
            modifier = Modifier.padding(paddingValues)
        )
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

val mockPortfolioItems = listOf(
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




