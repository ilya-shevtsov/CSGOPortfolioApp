package com.example.csgocaseswatcherapp.presentation.view.fragments.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.prederredcurrencydto.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.PortfolioViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StartViewModel : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<StartViewEvent>()

    fun handleAction(action: StartViewAction) {
        when (action) {
            is StartViewAction.OnCurrencySelected -> handleCurrencySelected(action)
            is StartViewAction.OnCaseOverviewClicked -> handleOnCaseOverviewClicked()
            is StartViewAction.OnPortfolioClicked -> handleOnPortfolioClickedClicked()
            is StartViewAction.OnAnalyticsClicked -> handleOnAnalyticsClickedClicked()
            is StartViewAction.OnCurrencyChangeClicked -> handleOnCurrencyChangeClickedClicked()
        }
    }

    private fun handleOnCurrencyChangeClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartViewEvent.NavigateToCurrencyChange) }
    }

    private fun handleOnAnalyticsClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartViewEvent.NavigateToAnalytics) }
    }

    private fun handleOnPortfolioClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartViewEvent.NavigateToPortfolio) }
    }

    private fun handleOnCaseOverviewClicked() {
        viewModelScope.launch { uiEvent.emit(StartViewEvent.NavigateToCaseOverview) }

    }

    private fun handleCurrencySelected(action: StartViewAction.OnCurrencySelected) {
        if (action.preferredCurrency != null) {
            uiState.value = StartViewState(action.preferredCurrency)

            when (action.preferredCurrency) {
                "USD" -> {
                    sendPreferredCurrency(PreferredCurrencyDto(1))
                    Log.e("ServerSide", "SendUSD")
                }
                "RUB" -> {
                    sendPreferredCurrency(PreferredCurrencyDto(5))
                    Log.e("ServerSide", "SendRUB")
                }
            }
        }
    }

    private fun sendPreferredCurrency(preferredCurrency: PreferredCurrencyDto) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiTools.getApiService().postPreferredCurrency(preferredCurrency)
        }
    }


    private fun createInitialState(): StartViewState = StartViewState("USD")

    suspend fun getPreferredCurrency() {
        viewModelScope.launch {
            val response = ApiTools.getApiService().getPreferredCurrency()
            val preferredCurrency = when (response.preferredCurrency) {
                1 -> "USD"
                5 -> "RUB"
                else -> { "Choose Currency" }
            }
            uiState.value = StartViewState(preferredCurrency)
        }
    }
}