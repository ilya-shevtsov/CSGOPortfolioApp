package com.example.csgocaseswatcherapp.features.portfolio.view

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem

sealed class PortfolioEvent {

    data object NavigateToAddCase : PortfolioEvent()

    data class NavigateToPortfolioDetails(
        val portfolioItemList: List<PortfolioItem>
    ) : PortfolioEvent()

    data object ScrollToTop : PortfolioEvent()
}