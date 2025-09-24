package com.example.csgocaseswatcherapp.features.start.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.navigation.Destination
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StartRoute(
    viewModel:StartViewModel,
    onNavigate: (Destination) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                StartEvent.NavigateToCaseOverview -> onNavigate(Destination.CaseOverView)
                StartEvent.NavigateToPortfolio -> onNavigate(Destination.Portfolio)
                StartEvent.NavigateToAnalytics -> onNavigate(Destination.CaseAnalytics)
                StartEvent.NavigateToCurrencyChange -> onNavigate(Destination.CurrencyChange)
            }
        }
    }

    StartScreen(state = state, onAction = viewModel::handleAction)
}