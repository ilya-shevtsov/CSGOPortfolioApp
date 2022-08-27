package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

sealed class CurrencyChangeViewEvent {

    data class NavigateToStartWithPreferredCurrency(
        val currencyName: String
    ) : CurrencyChangeViewEvent()
}