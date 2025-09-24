package com.example.csgocaseswatcherapp.features.currencychange.view

sealed class CurrencyChangeEvent {

    data class NavigateToStartWithPreferredCurrency(
        val currencyName: String
    ) : CurrencyChangeEvent()
}