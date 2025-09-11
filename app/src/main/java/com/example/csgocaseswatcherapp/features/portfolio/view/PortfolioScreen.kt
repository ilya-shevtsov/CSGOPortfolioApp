package com.example.csgocaseswatcherapp.features.portfolio.view

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.Locale

@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onAction: (PortfolioViewAction) -> Unit,
    listState: LazyListState
) {

    when (state) {
        is PortfolioViewState.Loading -> LoadingScreen()
        is PortfolioViewState.Error -> ErrorScreen()
        is PortfolioViewState.Content -> PortfolioContent(
            state = state,
            listState = listState,
            onAction = onAction,
        )
    }
}


@Composable
fun PortfolioContent(
    state: PortfolioViewState.Content,
    onAction: (PortfolioViewAction) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .padding(AppTheme.dimensions.paddingM)
    ) {
        Spacer(Modifier.height(8.dp))
        Text(
            text = state.totalPortfolioValue,
            color = AppTheme.colors.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.dimensions.paddingML)
        )
        HorizontalDivider(
            Modifier.padding(vertical = AppTheme.dimensions.paddingM),
            thickness = 1.dp
        )

        Spacer(Modifier.height(8.dp))

        PortfolioBarChart(
            entries = state.portfolioBartEntryList,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Spacer(Modifier.height(8.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
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
                    onClick = { onAction(PortfolioViewAction.OnPortfolioDetailsClicked) },
                    icon = Icons.AutoMirrored.Filled.List,
                    text = stringResource(R.string.details_button)
                )

                PortfolioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { onAction(PortfolioViewAction.OnSortClicked) },
                    icon = Icons.AutoMirrored.Filled.Sort,
                    text = stringResource(R.string.sorting_button)
                )
                PortfolioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { onAction(PortfolioViewAction.OnAddCaseClicked) },
                    icon = Icons.Default.Add,
                    text = stringResource(R.string.add_case_button)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = AppTheme.dimensions.paddingL),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = state.portfolioItemModelList,
                key = { it.itemName }
            ) { item ->
                PortfolioItemCard(
                    item = item,
                )
            }
        }
    }
}

@Composable
fun PortfolioButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(44.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary
        ),
        contentPadding = PaddingValues(
            horizontal = AppTheme.dimensions.paddingML,
            vertical = AppTheme.dimensions.paddingM
        )
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(6.dp))
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun PortfolioItemCard(
    item: PortfolioItemModel,
    modifier: Modifier = Modifier
) {
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
                .padding(AppTheme.dimensions.paddingML),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.itemImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.case_placeholder),
                modifier = Modifier
                    .size(width = 85.dp, height = 66.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.amountPrice,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = item.totalValue,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = AppTheme.colors.onSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = item.profitLoss,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppTheme.colors.onSurface
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
    val label = stringResource(R.string.portfolio_value)

    val cs = AppTheme.colors
    val barColor = cs.primary
    val valueColor = cs.onSurface
    val bgColor = cs.surface
    val axisColor = cs.onSurface.copy(alpha = 0.75f)

    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                description = Description().apply { text = "" }
                legend.isEnabled = false
                setDrawGridBackground(false)

                setBackgroundColor(bgColor.toArgb())

                axisRight.isEnabled = false
                axisLeft.apply {
                    axisMinimum = 0f
                    textColor = axisColor.toArgb()
                    setDrawAxisLine(false)
                }
                xAxis.apply {
                    isEnabled = false
                    textColor = axisColor.toArgb()
                }
            }
        },
        update = { chart ->
            val set = BarDataSet(entries, label).apply {
                color = barColor.toArgb()
                valueTextSize = 10f
                valueTextColor = valueColor.toArgb()
                setDrawValues(true)

                valueFormatter = object : ValueFormatter() {
                    override fun getBarLabel(e: BarEntry): String =
                        String.format(Locale.US, "$%.2f", e.y)
                }
            }

            chart.data = BarData(set).apply {
                barWidth = 0.6f
            }

            chart.invalidate()
            chart.animateY(1200, Easing.EaseInOutQuad)
        }
    )
}

@PreviewLightDark
@Composable
fun PortfolioScreenPreview() {
    AppTheme {
        val listState = rememberLazyListState()

        PortfolioScreen(
            state = PortfolioViewState.Content(
               portfolioBartEntryList = listOf(
                    BarEntry(1f, 129f),
                    BarEntry(2f, 164f),
                    BarEntry(3f, 225f),
                    BarEntry(4f, 236f),
                    BarEntry(5f, 334f),
                    BarEntry(6f, 479f),
                    BarEntry(7f, 429f),
                    BarEntry(8f, 424f),
                    BarEntry(9f, 448f),
                    BarEntry(10f, 335f),
                    BarEntry(11f, 315f),
                    BarEntry(12f, 322f),
                    BarEntry(13f, 414f),
                    BarEntry(14f, 458f),
                    BarEntry(15f, 509f),
                    BarEntry(16f, 546f),
                    BarEntry(17f, 668f),
                    BarEntry(18f, 741f),
                    BarEntry(19f, 685f),
                    BarEntry(20f, 840f),
                    BarEntry(21f, 834f),
                ),
                totalPortfolioValue = "Total: $10000.00", portfolioItemModelList = listOf(
                    PortfolioItemModel(
                        itemImage = "",
                        itemName = "Chroma Case 2",
                        totalValue = "$60.00",
                        amountPrice = "23 cases • $12.00",
                        profitLoss = "12.00 $ (23.23 %)"
                    ),
                    PortfolioItemModel(
                        itemImage = "",
                        itemName = "Chroma Case",
                        totalValue = "$60.00",
                        amountPrice = "23 cases • $12.00",
                        profitLoss = "12.00 $ (23.23 %)"
                    ),
                )
            ),
            onAction = {}, listState = listState
        )
    }
}



