package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsItem
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel

@Composable
fun CaseAnalyticsScreen(
    state: CaseAnalyticsViewState,
    deviceConfigurationType: DeviceConfigurationType
) {
    when (state) {
        is CaseAnalyticsViewState.Error -> ErrorScreen()
        is CaseAnalyticsViewState.Loading -> LoadingScreen()
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
    LazyColumn(modifier = Modifier.background(AppTheme.colors.background)) {
        items(
            items = items,
        ) { item ->
            CaseAnalyticsItem(
                item = item,
                deviceConfigurationType = deviceConfigurationType
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CaseAnalyticsScreenPreview() {
    AppTheme {
        val item = CaseAnalyticsModel(
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
        CaseAnalyticsScreen(
            state = CaseAnalyticsViewState.Content(
                caseAnalyticsItemList = listOf(
                    item,
                    item,
                    item,
                    item,
                    item,
                )
            ),
            deviceConfigurationType = DeviceConfigurationType.MOBILE_PORTRAIT
        )
    }
}