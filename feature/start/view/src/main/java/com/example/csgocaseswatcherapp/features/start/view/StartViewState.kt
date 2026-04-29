package com.example.csgocaseswatcherapp.features.start.view

sealed class StartViewState {

    data class Content(
        val currencyButton: String
    ) : StartViewState()

    data object Loading : StartViewState()

    data object Error : StartViewState()
}

