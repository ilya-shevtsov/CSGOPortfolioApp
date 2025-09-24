package com.example.csgocaseswatcherapp.features.start.view

sealed class StartEvent {

    data object NavigateToCaseOverview : StartEvent()

    data object NavigateToPortfolio : StartEvent()

    data object NavigateToAnalytics : StartEvent()

    data object NavigateToCurrencyChange : StartEvent()
}