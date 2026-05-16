package com.example.csgocaseswatcherapp.features.portfolio.domain.model

data class PortfolioState(
    val portfolioItemsResult: PortfolioItemsResult,
    val totalPortfolioValue: Double,
    val sortType: PortfolioSortType
)