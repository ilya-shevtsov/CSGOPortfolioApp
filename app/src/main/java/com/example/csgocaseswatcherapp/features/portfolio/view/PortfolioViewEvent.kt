package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioViewEvent {

    data object NavigateToAddCase : PortfolioViewEvent()

    data class NavigateToPortfolioDetails(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioViewEvent()

    data object ScrollToTop: PortfolioViewEvent()
}