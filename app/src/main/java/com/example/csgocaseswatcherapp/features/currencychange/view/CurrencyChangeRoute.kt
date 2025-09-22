package com.example.csgocaseswatcherapp.features.currencychange.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.navigation.Destination
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CurrencyChangeRoute(
    viewModel: CurrencyChangeViewModel,
    navigateToStart: (Destination) -> Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is CurrencyChangeEvent.NavigateToStartWithPreferredCurrency -> navigateToStart(
                    Destination.Start(event.currencyName)
                )
            }
        }
    }

    CurrencyChangeScreen(
        state = state,
        onCurrencyClicked = { clicked ->
            viewModel.handleAction(
                CurrencyChangeAction.OnCurrencyClicked(
                    clicked
                )
            )
        }
    )
}