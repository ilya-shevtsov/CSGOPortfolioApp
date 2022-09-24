package com.example.csgocaseswatcherapp.features.currencychange.view

sealed class CurrencyChangeViewAction {

    data class OnCurrencyClicked(
        val preferredCurrency: String
    ) : CurrencyChangeViewAction()
}