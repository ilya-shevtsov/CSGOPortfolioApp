package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem

sealed class PortfolioDetailsAction {

    data class OnPortfolioDataProvided(
        val portfolioItemList: List<PortfolioItem>
    ) : PortfolioDetailsAction()
}