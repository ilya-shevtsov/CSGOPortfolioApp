package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.PortfolioItemListArgs

sealed class PortfolioViewEvent {

    object NavigateToAddCase : PortfolioViewEvent()

    object NavigateToSorting : PortfolioViewEvent()

    data class NavigateToPortfolioDetails(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioViewEvent()

}