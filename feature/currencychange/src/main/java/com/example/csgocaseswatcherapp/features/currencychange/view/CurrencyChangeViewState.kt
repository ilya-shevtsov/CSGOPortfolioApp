package com.example.csgocaseswatcherapp.features.currencychange.view


sealed class CurrencyChangeViewState {
    data object Loading : CurrencyChangeViewState()

    data class Content(
        val currencyList: List<String>
    ) : CurrencyChangeViewState()

}

