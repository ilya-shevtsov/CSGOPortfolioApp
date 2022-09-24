package com.example.csgocaseswatcherapp.screens.start.view

sealed class StartViewEvent {

    object NavigateToCaseOverview : StartViewEvent()

    object NavigateToPortfolio : StartViewEvent()

    object NavigateToAnalytics : StartViewEvent()

    object NavigateToCurrencyChange : StartViewEvent()
}