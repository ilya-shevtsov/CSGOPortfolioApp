package com.example.csgocaseswatcherapp.screens.currencychange.view

sealed class CurrencyChangeViewAction {

    data class OnCurrencyClicked(
        val preferredCurrency: String
    ) : CurrencyChangeViewAction()
}