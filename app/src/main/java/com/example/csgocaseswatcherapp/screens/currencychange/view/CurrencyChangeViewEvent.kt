package com.example.csgocaseswatcherapp.screens.currencychange.view

sealed class CurrencyChangeViewEvent {

    data class NavigateToStartWithPreferredCurrency(
        val currencyName: String
    ) : CurrencyChangeViewEvent()
}