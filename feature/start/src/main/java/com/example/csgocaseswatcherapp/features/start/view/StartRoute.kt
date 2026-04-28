package com.example.csgocaseswatcherapp.features.start.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StartRoute(
    viewModel:StartViewModel,
    onNavigateToCaseOverview: () -> Unit,
    onNavigateToCaseAnalytics: () -> Unit,
    onNavigateToPortfolio: () -> Unit,
    onNavigateToCurrencyChange: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                StartEvent.NavigateToCaseOverview -> onNavigateToCaseOverview()
                StartEvent.NavigateToPortfolio -> onNavigateToPortfolio()
                StartEvent.NavigateToAnalytics -> onNavigateToCaseAnalytics()
                StartEvent.NavigateToCurrencyChange -> onNavigateToCurrencyChange()
            }
        }
    }

    StartScreen(state = state, onAction = viewModel::handleAction)
}