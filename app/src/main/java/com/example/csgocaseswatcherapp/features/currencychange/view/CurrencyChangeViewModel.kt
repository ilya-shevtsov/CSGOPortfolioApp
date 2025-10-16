package com.example.csgocaseswatcherapp.features.currencychange.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.usecases.SendPreferredCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyChangeViewModel @Inject constructor(
    private val sendPreferredCurrencyUseCase: SendPreferredCurrencyUseCase

) : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<CurrencyChangeEvent>()

    fun handleAction(action: CurrencyChangeAction) {
        when (action) {
            is CurrencyChangeAction.OnCurrencyClicked -> handleCurrencyItemClicked(action.preferredCurrency)
        }
    }

    private fun handleCurrencyItemClicked(currencyName: String) {
        viewModelScope.launch {
            when (currencyName) {
                "USD" -> {
                    sendPreferredCurrencyUseCase(PreferredCurrency(USD_VALUE))
                }
                "RUB" -> {
                    sendPreferredCurrencyUseCase(PreferredCurrency(RUB_VALUE))
                }
            }
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

    companion object {
        const val USD_VALUE = 1
        const val RUB_VALUE = 1
    }

}