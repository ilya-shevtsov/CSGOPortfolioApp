package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioViewEvent {

    object NavigateToAddCase : PortfolioViewEvent()

    object NavigateToSorting : PortfolioViewEvent()
    data class NavigateToPortfolioDetails(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioViewEvent()
}