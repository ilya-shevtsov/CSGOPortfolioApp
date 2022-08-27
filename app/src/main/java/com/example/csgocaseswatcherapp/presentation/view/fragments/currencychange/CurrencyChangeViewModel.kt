package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CurrencyChangeViewModel : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<CurrencyChangeViewEvent>()

    fun handleAction(action: CurrencyChangeViewAction) {
        when (action) {
            is CurrencyChangeViewAction.OnCurrencyClicked -> handleCurrencyItemClicked(action.preferredCurrency)
        }
    }

    private fun handleCurrencyItemClicked(currencyName: String) {
        viewModelScope.launch {
            uiEvent.emit(
                CurrencyChangeViewEvent.NavigateToStartWithPreferredCurrency(
                    currencyName
                )
            )
        }
    }


    //PlaceHolder for currencies (later get from database)

    private fun createInitialState(): CurrencyChangeViewState =
        CurrencyChangeViewState(listOf("USD", "RUB"))

}