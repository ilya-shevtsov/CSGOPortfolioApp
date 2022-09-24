package com.example.csgocaseswatcherapp.presentation.screens.currencychange.view

sealed class CurrencyChangeViewAction {

    data class OnCurrencyClicked(
        val preferredCurrency: String
    ) : CurrencyChangeViewAction()
}