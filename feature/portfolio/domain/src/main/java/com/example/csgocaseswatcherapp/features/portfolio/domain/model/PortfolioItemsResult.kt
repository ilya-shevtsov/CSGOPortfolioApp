package com.example.csgocaseswatcherapp.features.portfolio.domain.model

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem

sealed interface PortfolioItemsResult {
    data object Loading : PortfolioItemsResult
    data class Success(val portfolioItemList: List<PortfolioItem>) : PortfolioItemsResult
    data class Error(val message: String?) : PortfolioItemsResult
}