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
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsItem
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsItemShimmer
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

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
    dailyAvgReturnInPercent = "0.14 %",
    dailyAvgReturnInRUB = "-0.31",
    dailyStandardDeviation = "0.06421",
    dailySharpRatio = "0.03216",
    monthlyAvgReturnInPercent = "4.11 %",
    monthlyAvgReturnInRUB = "-3.24",
    monthlyStandardDeviation = "0.22929",
    monthlySharpRatio = "0.21576",
    imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
)
