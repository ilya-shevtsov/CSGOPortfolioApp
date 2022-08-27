package com.example.csgocaseswatcherapp.presentation.view.fragments.start

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class StartViewModel() : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    fun handleAction(onCurrencySelected: StartViewAction.OnCurrencySelected) {
        if (onCurrencySelected.preferredCurrency != null) {
            uiState.value = StartViewState(onCurrencySelected.preferredCurrency)
        }
    }

    private fun createInitialState(): StartViewState = StartViewState("Choose Currency")

}