package com.example.csgocaseswatcherapp.features.portfoliodetails.domain

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem

data class PortfolioDetailsState(
    val portfolioItemList: List<PortfolioItem>
)
