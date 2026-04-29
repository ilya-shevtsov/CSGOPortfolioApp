package com.example.csgocaseswatcherapp.features.start.view

sealed class StartAction {

    data object OnCreate : StartAction()

    data object OnCaseOverviewClicked : StartAction()

    data object OnPortfolioClicked : StartAction()

    data object OnAnalyticsClicked : StartAction()

    data object OnCurrencyChangeClicked : StartAction()
}