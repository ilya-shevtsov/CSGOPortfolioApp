package com.example.csgocaseswatcherapp.features.caseanalytics.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CaseAnalyticsRoute(
    viewModel: CaseAnalyticsViewModel
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CaseAnalyticsScreen(
        state = state,
    )
}