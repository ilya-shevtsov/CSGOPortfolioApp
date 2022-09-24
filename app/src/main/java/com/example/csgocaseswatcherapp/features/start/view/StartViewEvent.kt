package com.example.csgocaseswatcherapp.features.start.view

sealed class StartViewEvent {

    object NavigateToCaseOverview : StartViewEvent()

    object NavigateToPortfolio : StartViewEvent()

    object NavigateToAnalytics : StartViewEvent()

    object NavigateToCurrencyChange : StartViewEvent()
}