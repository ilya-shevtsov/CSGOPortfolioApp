package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioDetailsViewAction()
}