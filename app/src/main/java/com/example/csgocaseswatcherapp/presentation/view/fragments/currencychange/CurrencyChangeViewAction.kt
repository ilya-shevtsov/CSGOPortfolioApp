package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

sealed class CurrencyChangeViewAction {

    data class OnCurrencyClicked(
        val preferredCurrency: String
    ) : CurrencyChangeViewAction()
}