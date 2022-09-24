package com.example.csgocaseswatcherapp.features.start.view

sealed class StartViewState {

    data class Content(
        val currencyButton: String
    ) : StartViewState()

    object Loading : StartViewState()

    object Error : StartViewState()
}

