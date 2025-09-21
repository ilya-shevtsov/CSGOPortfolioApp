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
    currency: String?,
    onNavigate: (Destination) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(currency) {
        viewModel.handleAction(StartViewAction.OnCurrencySelected(currency))
    }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                StartViewEvent.NavigateToCaseOverview -> onNavigate(Destination.CaseOverView)
                StartViewEvent.NavigateToPortfolio -> onNavigate(Destination.Portfolio)
                StartViewEvent.NavigateToAnalytics -> onNavigate(Destination.CaseAnalytics)
                StartViewEvent.NavigateToCurrencyChange -> onNavigate(Destination.CurrencyChange)
            }
        }
    }

    StartScreen(state = state, onAction = viewModel::handleAction)
}