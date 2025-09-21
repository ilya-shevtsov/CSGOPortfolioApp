package com.example.csgocaseswatcherapp.features.start.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun StartRoute(
    viewModel:StartViewModel,
    currency: String?,
    onNavigate: (Screen) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(currency) {
        viewModel.handleAction(StartViewAction.OnCurrencySelected(currency))
    }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                StartViewEvent.NavigateToCaseOverview -> onNavigate(Screen.CaseOverView)
                StartViewEvent.NavigateToPortfolio -> onNavigate(Screen.Portfolio)
                StartViewEvent.NavigateToAnalytics -> onNavigate(Screen.CaseAnalytics)
                StartViewEvent.NavigateToCurrencyChange -> onNavigate(Screen.CurrencyChange)
            }
        }
    }

    StartScreen(state = state, onAction = viewModel::handleAction)
}