package com.example.csgocaseswatcherapp.features.currencychange.view

sealed class CurrencyChangeViewEvent {

    data class NavigateToStartWithPreferredCurrency(
        val currencyName: String
    ) : CurrencyChangeViewEvent()
}