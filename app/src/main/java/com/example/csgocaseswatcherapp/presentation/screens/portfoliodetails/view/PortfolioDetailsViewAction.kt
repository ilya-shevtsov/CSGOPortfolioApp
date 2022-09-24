package com.example.csgocaseswatcherapp.presentation.screens.portfoliodetails.view

import com.example.csgocaseswatcherapp.presentation.model.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioDetailsViewAction()
}