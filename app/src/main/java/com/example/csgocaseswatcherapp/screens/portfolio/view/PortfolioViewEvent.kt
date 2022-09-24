package com.example.csgocaseswatcherapp.screens.portfolio.view

import com.example.csgocaseswatcherapp.screens.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioViewEvent {

    object NavigateToAddCase : PortfolioViewEvent()

    object NavigateToSorting : PortfolioViewEvent()

    data class NavigateToPortfolioDetails(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioViewEvent()
}