package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.shimmer.ShimmerList
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsDailyModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsItem
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsItemShimmer
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.model.CaseAnalyticsMonthlyModel

@Composable
fun CaseAnalyticsScreen(
    state: CaseAnalyticsViewState,
    deviceConfigurationType: DeviceConfigurationType
) {
    when (state) {
        is CaseAnalyticsViewState.Error -> ErrorScreen()
        is CaseAnalyticsViewState.Loading -> CaseAnalyticsScreenShimmer(
            deviceConfigurationType = deviceConfigurationType
        )

        is CaseAnalyticsViewState.Content -> {
            CaseAnalyticsScreenContent(
                items = state.caseAnalyticsItemList,
                deviceConfigurationType = deviceConfigurationType
            )
        }
    }
}

@Composable
fun CaseAnalyticsScreenContent(
    items: List<CaseAnalyticsModel>,
    deviceConfigurationType: DeviceConfigurationType
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentPadding = PaddingValues(AppTheme.dimensions.paddingM),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingML)
    ) {
        items(
            items = items,
        ) { item ->
            CaseAnalyticsItem(
                item = item,
                deviceConfigurationType = deviceConfigurationType,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CaseAnalyticsScreenShimmer(deviceConfigurationType: DeviceConfigurationType) {
    ShimmerList(
        itemCount = 3
    ) {
        CaseAnalyticsItemShimmer(deviceConfigurationType)
    }
}

@PreviewPortraitLandscapeDarkLight
@Composable
private fun CaseAnalyticsScreenPreview() {
    PreviewScreenWithTopBar(
        title = "Case Overview",
        canNavigateBack = true
    ) { deviceConfigurationType, _ ->
        CaseAnalyticsScreen(
            state = CaseAnalyticsViewState.Content(
                caseAnalyticsItemList = listOf(
                    mockItem,
                    mockItem,
                    mockItem,
                    mockItem,
                    mockItem,
                )
            ),
            deviceConfigurationType = deviceConfigurationType
        )
    }
}

@PreviewPortraitLandscapeDarkLight
@Composable
private fun CaseAnalyticsScreenShimmerPreview() {
    PreviewScreenWithTopBar(
        title = "Case Overview",
        canNavigateBack = true
    ) { deviceConfigurationType, _ ->
        CaseAnalyticsScreen(
            state = CaseAnalyticsViewState.Loading,
            deviceConfigurationType = deviceConfigurationType
        )
    }
}

val mockItem = CaseAnalyticsModel(
    caseName = "Chroma Case",
    imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case",
    dailyData = CaseAnalyticsDailyModel(
        avgReturnInPercent = "0.14 %",
        avgReturnInRUB = "-0.31",
        standardDeviation = "0.06421",
        sharpRatio = "0.03216"
    ),
    monthlyData = CaseAnalyticsMonthlyModel(
        avgReturnInPercent = "4.11 %",
        avgReturnInRUB = "-3.24",
        standardDeviation = "0.22929",
        sharpRatio = "0.21576",
    )
)
