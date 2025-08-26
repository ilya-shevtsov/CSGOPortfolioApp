package com.example.csgocaseswatcherapp.features.portfolio.view

import android.graphics.Color
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.util.Locale

@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onDetailsClicked: () -> Unit,
    onAddCaseClicked: () -> Unit,
    onSortingClicked: () -> Unit,
) {

    when (state) {
        is PortfolioViewState.Loading -> LoadingScreen()
        is PortfolioViewState.Error -> ErrorScreen()
        is PortfolioViewState.Content -> PortfolioContent(

            totalPortfolioValue = state.totalPortfolioValue,
            items = state.portfolioItemList,
            barEntries = state.portfolioBartEntryList,
            onDetailsClicked = onDetailsClicked,
            onAddCaseClicked = onAddCaseClicked,
            onSortingClicked = onSortingClicked,
            modifier = Modifier
        )
    }
}


@Composable
fun PortfolioContent(
    modifier: Modifier,
    totalPortfolioValue: Double,
    items: List<PortfolioItem>,
    barEntries: List<BarEntry>,
    onDetailsClicked: () -> Unit,
    onAddCaseClicked: () -> Unit,
    onSortingClicked: () -> Unit
) {
    Column(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Total: " + String.format(Locale.US, "$%.2f", totalPortfolioValue),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        HorizontalDivider(Modifier.padding(vertical = 8.dp), thickness = 1.dp)

        Spacer(Modifier.height(8.dp))

        PortfolioBarChart(
            entries = barEntries,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Spacer(Modifier.height(8.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PortfolioButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDetailsClicked,
                    icon = Icons.AutoMirrored.Default.List,
                    text = stringResource(R.string.details_button)
                )

                PortfolioButton(
                    modifier = Modifier.weight(1f),
                    onClick = onSortingClicked,
                    icon = Icons.AutoMirrored.Filled.Sort,
                    text = stringResource(R.string.sorting_button)
                )
                PortfolioButton(
                    modifier = Modifier.weight(1f),
                    onClick = onAddCaseClicked,
                    icon = Icons.Default.Add,
                    text = stringResource(R.string.add_case_button)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = items,
                key = { it.caseName }
            ) { item ->
                PortfolioItemCard(
                    item = item,
                )
            }
        }
    }
}

@Composable
fun PortfolioButton(modifier: Modifier, onClick: () -> Unit, icon: ImageVector, text: String) {
    Button(
        onClick = onClick,
        modifier = modifier

            .height(44.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(6.dp))
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun PortfolioItemCard(
    item: PortfolioItem,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.caseImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.d_chroma_case),
                modifier = Modifier
                    .size(width = 85.dp, height = 66.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.caseName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${item.caseAmount} cases • ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = String.format(Locale.US, "$%.2f", item.casePrice),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = String.format(Locale.US, "$%.2f", item.caseOverallValue),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(2.dp))
                val sign = if (item.caseProfitLoss >= 0) "+" else ""
                Text(
                    text = "$sign${
                        String.format(
                            Locale.US,
                            "%.2f",
                            item.caseProfitLoss
                        )
                    } $ (${item.caseProfitLoss} %)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color.Black
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
    AndroidView(
        modifier = modifier,
        factory = { context ->
            BarChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                description = Description().apply { text = "" }
                legend.isEnabled = false
                axisRight.isEnabled = false
                axisLeft.axisMinimum = 0f
                xAxis.isEnabled = false
                setDrawGridBackground(false)
            }
        },
        update = { chart ->
            val set = BarDataSet(entries, "Portfolio Value").apply {
                color = Color.parseColor("#2FA1BA")
                valueTextSize = 10f
                valueTextColor = Color.BLACK
            }
            chart.data = BarData(set)
            chart.invalidate()
            chart.animateY(1200, Easing.EaseInOutQuad)
        }
    )
}

@Preview
@Composable
fun PortfolioScreenPreview() {
    AppTheme {
        PortfolioScreen(
            state = PortfolioViewState.Content(
                portfolioItemList = listOf(
                    PortfolioItem(
                        caseImage = "https://api.steamapis.com/image/item/730/Horizon%20Case",
                        caseName = "Horizon Case",
                        caseAmount = 20,
                        casePrice = 3.0,
                        caseOverallValue = 60.0,
                        caseProfitLoss = 2.9
                    ),
                    PortfolioItem(
                        caseImage = "https://api.steamapis.com/image/item/730/Chroma%20Case",
                        caseName = "Chroma Case",
                        caseAmount = 2,
                        casePrice = 1.0,
                        caseOverallValue = 2.0,
                        caseProfitLoss = 3.1
                    )
                ), portfolioValueList = emptyList(), portfolioBartEntryList = listOf(
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
                totalPortfolioValue = 62.0
            ),
            onDetailsClicked = {},
            onSortingClicked = {},
            onAddCaseClicked = {}
        )
    }
}

