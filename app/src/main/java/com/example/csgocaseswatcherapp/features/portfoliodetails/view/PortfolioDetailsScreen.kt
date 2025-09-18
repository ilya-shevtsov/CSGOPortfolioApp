package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.viewinterop.AndroidView
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun PortfolioDetailsScreen(
    state: PortfolioDetailsViewState
) {
    when (state) {
        is PortfolioDetailsViewState.Error -> ErrorScreen()
        is PortfolioDetailsViewState.Loading -> LoadingScreen()
        is PortfolioDetailsViewState.Content -> PortfolioDetailsContent(
            portfolioPietEntryList = state.portfolioPietEntryList,
            modifier = Modifier
        )

    }
}

@Composable
fun PortfolioDetailsContent(
    portfolioPietEntryList: List<PieEntry>,
    modifier: Modifier = Modifier
) {
    val label = stringResource(R.string.portfolio_pie_chart_amount)

    val theme = AppTheme.colors


    AndroidView(
        modifier = modifier
            .background(AppTheme.colors.background)
            .fillMaxSize()
            .padding(AppTheme.dimensions.paddingM),
        factory = { context ->
            PieChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                val onSurfaceInt = theme.onSurface.toArgb()
                val surfaceInt = theme.surface.toArgb()

                setBackgroundColor(android.graphics.Color.TRANSPARENT)

                isDrawHoleEnabled = true
                setHoleColor(surfaceInt)
                setTransparentCircleColor(theme.surface.copy(alpha = 0.40f).toArgb())
                setTransparentCircleAlpha(100)

                setUsePercentValues(true)
                setEntryLabelTextSize(12f)
                setEntryLabelColor(onSurfaceInt)
                centerText = label
                setCenterTextSize(24f)
                setCenterTextColor(onSurfaceInt)

                description.isEnabled = false
                legend.isEnabled = false

                setNoDataTextColor(theme.onBackground.toArgb())
            }
        },
        update = { chart ->
            val dataSet = PieDataSet(portfolioPietEntryList, label).apply {
                this.colors = ColorTemplate.MATERIAL_COLORS.toList()
                sliceSpace = 1f
                selectionShift = 6f
                xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                valueLineColor = theme.onSurface.toArgb()
                valueLineWidth = 1f
            }


            chart.apply {
                setEntryLabelColor(theme.onSurface.toArgb())
                setCenterTextColor(theme.onSurface.toArgb())
                setHoleColor(theme.surface.toArgb())
                setTransparentCircleColor(theme.surface.copy(alpha = 0.40f).toArgb())
                setNoDataTextColor(theme.onBackground.toArgb())
            }

            chart.data = PieData(dataSet).apply {
                setDrawValues(true)
                setValueFormatter(PercentFormatter(chart))
                setValueTextSize(12f)
                setValueTextColor(theme.onSurface.toArgb())
            }


             chart.legend.textColor = theme.onSurface.toArgb()
             chart.description.textColor = theme.onSurface.toArgb()

            chart.invalidate()
            chart.animateY(1000, Easing.EaseInOutQuad)
        }
    )
}

@PreviewLightDark
@Composable
fun PortfolioDetailsScreenPreview() {
    AppTheme {
        PortfolioDetailsScreen(
            state = PortfolioDetailsViewState.Content(
                portfolioPietEntryList = listOf(
                    PieEntry(0.0f, 255.0f),
                    PieEntry(0.0f, 20.0f),
                    PieEntry(0.0f, 1.0f),
                    PieEntry(0.0f, 10.0f),
                    PieEntry(0.0f, 2.0f)
                )
            )
        )
    }
}