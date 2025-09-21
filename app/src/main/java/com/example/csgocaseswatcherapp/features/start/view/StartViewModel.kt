package com.example.csgocaseswatcherapp.features.start.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.usecases.GetPreferredCurrencyUseCase
import com.example.csgocaseswatcherapp.features.start.domain.usecases.SendPreferredCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class StartViewModel @Inject constructor(
    private val getPreferredCurrencyUseCase: GetPreferredCurrencyUseCase,
    private val sendPreferredCurrencyUseCase: SendPreferredCurrencyUseCase
) : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<StartEvent>()

    init {
        viewModelScope.launch {
            try {
                val preferredCurrency = when (getPreferredCurrencyUseCase().preferredCurrency) {
                    1 -> "USD"
                    5 -> "RUB"
                    else -> {
                        "Choose Currency"
                    }
                }
                uiState.value = StartViewState.Content(preferredCurrency)
            } catch (throwable: Throwable) {
                showError()
            }
        }
    }

    fun handleAction(action: StartAction) {
        when (action) {
            is StartAction.OnCurrencySelected -> handleCurrencySelected(action)
            is StartAction.OnCaseOverviewClicked -> handleOnCaseOverviewClicked()
            is StartAction.OnPortfolioClicked -> handleOnPortfolioClickedClicked()
            is StartAction.OnAnalyticsClicked -> handleOnAnalyticsClickedClicked()
            is StartAction.OnCurrencyChangeClicked -> handleOnCurrencyChangeClickedClicked()
        }
    }

    private fun handleOnCurrencyChangeClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartEvent.NavigateToCurrencyChange) }
    }

    private fun handleOnAnalyticsClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartEvent.NavigateToAnalytics) }
    }

    private fun handleOnPortfolioClickedClicked() {
        viewModelScope.launch { uiEvent.emit(StartEvent.NavigateToPortfolio) }
    }

    private fun handleOnCaseOverviewClicked() {
        viewModelScope.launch { uiEvent.emit(StartEvent.NavigateToCaseOverview) }

    }

    private fun handleCurrencySelected(action: StartAction.OnCurrencySelected) {
        if (action.preferredCurrency != null) {
            uiState.value = StartViewState.Content(action.preferredCurrency)

            when (action.preferredCurrency) {
                "USD" -> {
                    sendPreferredCurrencyUseCase(PreferredCurrency(1))
                }
                "RUB" -> {
                    sendPreferredCurrencyUseCase(PreferredCurrency(5))
                }
            }
        }
    }

    private fun createInitialState(): StartViewState = StartViewState.Content("USD")

    private fun showError() {
        uiState.value = StartViewState.Error
    }
}
