package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.ui.rememberDeviceConfigurationType

@Composable
fun CaseAnalyticsRoute(
    viewModel: CaseAnalyticsViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val deviceConfigurationType = rememberDeviceConfigurationType()

    CaseAnalyticsScreen(
        state = state,
        deviceConfigurationType = deviceConfigurationType
    )
}