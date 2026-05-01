package com.example.csgocaseswatcherapp.features.caseoverview.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.ui.rememberDeviceConfigurationType
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CaseOverViewRoute(
    viewModel: CaseOverviewViewModel,
    onNavigateToDetails: (CaseOverviewModel) -> Unit,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val deviceConfigurationType = rememberDeviceConfigurationType()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is CaseOverviewEvent.NavigateToCaseDetails -> onNavigateToDetails(
                    event.case
                )
            }
        }
    }

    CaseOverviewScreen(
        state = state,
        onCaseClick = { clicked ->
            viewModel.handleAction(CaseOverviewAction.OnCaseClicked(clicked))
        },
        deviceConfigurationType = deviceConfigurationType
    )

}