package com.example.csgocaseswatcherapp.features.portfolio.domain.model

import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem

fun List<PortfolioItem>.sortBy(sortType: PortfolioSortType): List<PortfolioItem> {
    return when (sortType) {
        PortfolioSortType.NAME -> sortedBy { it.name }
        PortfolioSortType.AMOUNT -> sortedByDescending { it.amount }
        PortfolioSortType.PRICE -> sortedByDescending { it.price }
        PortfolioSortType.OVERALL_VALUE -> sortedByDescending { it.overallValue }
        PortfolioSortType.PROFIT_LOSS -> sortedByDescending { it.profitLoss }
    }
}

//TODO: this is a calculated here for now, since backend does not supply the % profit/loss.
val PortfolioItem.profitLossPercent: Double
    get() {
        val investedValue = overallValue - profitLoss
        if (investedValue == 0.0) return 0.0
        return (profitLoss / investedValue) * 100
    }