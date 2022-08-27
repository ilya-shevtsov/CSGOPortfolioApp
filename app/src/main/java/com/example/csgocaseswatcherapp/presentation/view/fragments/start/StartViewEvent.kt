package com.example.csgocaseswatcherapp.presentation.view.fragments.start

sealed class StartViewEvent {

    object NavigateToCaseOverview : StartViewEvent()

    object NavigateToPortfolio : StartViewEvent()

    object NavigateToAnalytics : StartViewEvent()

    object NavigateToCurrencyChange : StartViewEvent()
}