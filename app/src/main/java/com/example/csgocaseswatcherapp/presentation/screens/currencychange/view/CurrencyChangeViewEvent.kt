package com.example.csgocaseswatcherapp.presentation.screens.currencychange.view

sealed class CurrencyChangeViewEvent {

    data class NavigateToStartWithPreferredCurrency(
        val currencyName: String
    ) : CurrencyChangeViewEvent()
}