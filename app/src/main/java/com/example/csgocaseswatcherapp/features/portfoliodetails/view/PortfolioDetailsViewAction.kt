package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemListArgs

sealed class PortfolioDetailsViewAction {

    data class OnPortfolioDataProvided(
        val portfolioItemList: List<PortfolioItem>
    ) : PortfolioDetailsViewAction()
}