package com.example.csgocaseswatcherapp.screens.portfoliodetails.view

import com.example.csgocaseswatcherapp.screens.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioDetailsViewAction()
}