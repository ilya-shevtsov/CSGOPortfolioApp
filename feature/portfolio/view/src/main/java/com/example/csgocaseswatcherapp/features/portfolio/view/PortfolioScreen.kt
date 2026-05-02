package com.example.csgocaseswatcherapp.features.portfolio.view

import android.content.res.Configuration
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.example.csgocaseswatcherapp.core.ui.R as UiR


@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onAction: (PortfolioAction) -> Unit,
    listState: LazyListState,
    deviceConfigurationType: DeviceConfigurationType
) {

    when (state) {
        is PortfolioViewState.Loading -> LoadingScreen()
        is PortfolioViewState.Error -> ErrorScreen()
        is PortfolioViewState.Content -> PortfolioContent(
            state = state,
            listState = listState,
            onAction = onAction,
            deviceConfigurationType = deviceConfigurationType
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
            .navigationBarsPadding()
            .displayCutoutPadding()
            .padding(
                start = AppTheme.dimensions.paddingL,
                end = AppTheme.dimensions.paddingL,
                top = AppTheme.dimensions.paddingM,
                bottom = AppTheme.dimensions.paddingM
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        PortfolioLandscapeSummaryPanel(
            state = state,
            onAction = onAction,
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight()
        )

        PortfolioItemList(
            items = state.portfolioItemModelList,
            listState = listState,
            compact = true,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
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
            .navigationBarsPadding()
            .padding(AppTheme.dimensions.paddingM),
    ) {
        PortfolioValueHeader(
            totalPortfolioValue = state.totalPortfolioValue,
            showDivider = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppTheme.dimensions.paddingM))

        PortfolioBarChart(
            entries = state.portfolioBartEntryList,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

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
            showDivider = false,
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
        contentPadding = PaddingValues(bottom = AppTheme.dimensions.paddingM),
        verticalArrangement = Arrangement.spacedBy(
            if (compact) 8.dp else 12.dp
        )
    ) {
        items(
            items = items,
            key = { it.itemName }
        ) { item ->
            PortfolioItemCard(
                item = item,
                compact = compact
            )
        }
    }
}

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
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.paddingM),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
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

@Composable
fun PortfolioValueHeader(
    totalPortfolioValue: Double,
    showDivider: Boolean,
    modifier: Modifier
) {

    val totalPortfolioValueText = stringResource(
        id = R.string.portfolio_total_value,
        formatUsd(totalPortfolioValue)
    )
    Column(modifier = modifier) {
        Text(
            text = totalPortfolioValueText,
            color = AppTheme.colors.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = if (showDivider) 22.sp else 20.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
        )

        if (showDivider) {
            Spacer(modifier = Modifier.height(AppTheme.dimensions.paddingM))

            HorizontalDivider(
                thickness = 1.dp,
                color = AppTheme.colors.onBackground.copy(alpha = 0.35f)
            )
        }
    }
}


@Composable
fun PortfolioButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(
            if (compact) 40.dp else 44.dp
        ),
        shape = AppTheme.shapes.buttonRounded,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary
        ),
        contentPadding = PaddingValues(
            horizontal = if (compact) 8.dp else AppTheme.dimensions.paddingML,
            vertical = 0.dp
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(if (compact) 18.dp else 24.dp)
        )

        Spacer(Modifier.width(if (compact) 4.dp else 6.dp))

        Text(
            text = text,
            fontSize = if (compact) 11.sp else 12.sp,
            maxLines = 1
        )
    }
}

@Composable
fun PortfolioItemCard(
    item: PortfolioItemModel,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    val imageWidth = if (compact) 64.dp else 85.dp
    val imageHeight = if (compact) 50.dp else 66.dp

    val cardPadding = if (compact) {
        AppTheme.dimensions.paddingM
    } else {
        AppTheme.dimensions.paddingML
    }
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.itemImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(UiR.drawable.case_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(width = imageWidth, height = imageHeight)
            )

            Spacer(Modifier.width(if (compact) 8.dp else 12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.itemName,
                    style = if (compact) {
                        MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        MaterialTheme.typography.titleMedium
                    },
                    maxLines = if (compact) 1 else 2,
                    color = AppTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.portfolio_amount_price,
                        pluralStringResource(
                            id = R.plurals.portfolio_cases_count,
                            count = item.amount,
                            item.amount
                        ),
                        formatUsd(item.price)
                    ),
                    style = if (compact) {
                        MaterialTheme.typography.bodySmall
                    } else {
                        MaterialTheme.typography.bodyMedium
                    },
                    maxLines = 1,
                    color = AppTheme.colors.onSurface
                )
            }

            Spacer(modifier = Modifier.width(if (compact) 8.dp else 12.dp))

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatUsd(item.totalValue),
                    style = if (compact) {
                        MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    },
                    color = AppTheme.colors.onSurface,
                    maxLines = 1
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(
                        id = R.string.portfolio_profit_loss,
                        formatSignedUsd(item.profitLoss),
                        formatSignedPercent(item.profitLossPercent)
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = AppTheme.colors.onSurface,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun PortfolioBarChart(
    entries: List<BarEntry>,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        shape = MaterialTheme.shapes.large
    ) {
        val label = stringResource(R.string.portfolio_value)

        val colors = AppTheme.colors
        val barColor = colors.primary
        val backgroundColor = colors.surface
        val axisColor = colors.onSurface.copy(alpha = 0.75f)

        AndroidView(
            modifier = modifier,
            factory = { context ->
                BarChart(context).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                    description = Description().apply { text = "" }
                    legend.isEnabled = false

                    setDrawGridBackground(false)
                    setBackgroundColor(backgroundColor.toArgb())
                    setTouchEnabled(false)
                    setScaleEnabled(false)
                    setPinchZoom(false)
                    isDoubleTapToZoomEnabled = false

                    axisRight.isEnabled = false

                    axisLeft.apply {
                        axisMinimum = 0f
                        textColor = axisColor.toArgb()
                        setDrawAxisLine(false)
                        setDrawGridLines(false)
                    }

                    xAxis.apply {
                        isEnabled = false
                        textColor = axisColor.toArgb()
                    }
                }
            },
            update = { chart ->
                val dataSet = BarDataSet(entries, label).apply {
                    color = barColor.toArgb()
                    setDrawValues(false)
                }

                chart.data = BarData(dataSet).apply {
                    barWidth = 0.6f
                }

                chart.invalidate()
                chart.animateY(700, Easing.EaseInOutQuad)
            }
        )
    }
}

@Preview(
    name = "Portfolio - long preview light",
    showBackground = true,
    widthDp = 420,
    heightDp = 1600,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Portfolio - long preview dark",
    showBackground = true,
    widthDp = 420,
    heightDp = 1600,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PortfolioScreenPreview() {
    AppTheme {
        val listState = rememberLazyListState()

        PortfolioScreen(
            state = PortfolioViewState.Content(
                portfolioBartEntryList = listOf(
                    BarEntry(1f, 20f),
                    BarEntry(2f, 32f),
                    BarEntry(3f, 44f),
                    BarEntry(4f, 58f),
                ),
                totalPortfolioValue = 1_690.65,
                portfolioItemModelList = listOf(
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
            ),
            onAction = {},
            listState = listState,
            deviceConfigurationType = DeviceConfigurationType.MOBILE_PORTRAIT
        )
    }
}



