package com.example.csgocaseswatcherapp.features.currencychange.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyChangeViewModel @Inject constructor() : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<CurrencyChangeEvent>()

    fun handleAction(action: CurrencyChangeAction) {
        when (action) {
            is CurrencyChangeAction.OnCurrencyClicked -> handleCurrencyItemClicked(action.preferredCurrency)
        }
    }

    private fun handleCurrencyItemClicked(currencyName: String) {
        viewModelScope.launch {
            uiEvent.emit(
                CurrencyChangeEvent.NavigateToStartWithPreferredCurrency(
                    currencyName
                )
            )
        }
    }

    //PlaceHolder for currencies (later get from database)

    private fun createInitialState(): CurrencyChangeViewState {
        return CurrencyChangeViewState.Content(listOf("USD", "RUB"))
    }
}