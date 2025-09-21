package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioEvent {

    data object NavigateToAddCase : PortfolioEvent()

    data class NavigateToPortfolioDetails(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioEvent()

    data object ScrollToTop : PortfolioEvent()
}