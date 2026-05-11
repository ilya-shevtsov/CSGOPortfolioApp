package com.example.csgocaseswatcherapp.features.portfolio.view.components

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.view.model.PortfolioBarEntryModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.collections.immutable.PersistentList

@Composable
fun PortfolioBarChart(
    entries: PersistentList<PortfolioBarEntryModel>,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        shape = AppTheme.shapes.cardDefault
    ) {
        val label = stringResource(R.string.portfolio_value)

        val colors = AppTheme.colors
        val barColor = colors.primary
        val backgroundColor = colors.surface
        val axisColor = colors.onSurface.copy(alpha = 0.75f)

        AndroidView(
            modifier = Modifier.fillMaxSize(),
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
                val barEntries = entries.map { BarEntry(it.x, it.y) }
                val dataSet = BarDataSet(barEntries, label).apply {
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