package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel

@Composable
fun CaseOverviewDetailsRoute(
    viewModel: CaseDetailsViewModel,
    currentCase: CaseOverviewModel,
) {
    LaunchedEffect(currentCase) {
        viewModel.handleAction(CaseDetailsViewAction.OnItemProvided(currentCase))
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CaseDetailsScreen(
        state = state,
    )
}