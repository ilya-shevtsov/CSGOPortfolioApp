package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import com.example.csgocaseswatcherapp.presentation.model.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemListArgs: PortfolioItemListArgs
    ) : PortfolioDetailsViewAction()
}