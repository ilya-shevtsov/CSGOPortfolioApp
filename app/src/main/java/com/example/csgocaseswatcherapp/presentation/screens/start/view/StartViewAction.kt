package com.example.csgocaseswatcherapp.presentation.screens.start.view

sealed class StartViewAction {

    data class OnCurrencySelected(
        val preferredCurrency: String?
    ) : StartViewAction()

    object OnCaseOverviewClicked : StartViewAction()

    object OnPortfolioClicked : StartViewAction()

    object OnAnalyticsClicked : StartViewAction()

    object OnCurrencyChangeClicked : StartViewAction()
}