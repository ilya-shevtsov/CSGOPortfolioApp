package com.example.csgocaseswatcherapp.presentation.screens.portfoliodetails.view

import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioDetailsViewAction()
}