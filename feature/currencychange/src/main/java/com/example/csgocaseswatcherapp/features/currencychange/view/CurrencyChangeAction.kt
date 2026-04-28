package com.example.csgocaseswatcherapp.features.currencychange.view

sealed class CurrencyChangeAction {

    data class OnCurrencyClicked(
        val preferredCurrency: String
    ) : CurrencyChangeAction()
}