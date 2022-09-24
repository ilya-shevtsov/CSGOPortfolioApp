package com.example.csgocaseswatcherapp.screens.portfolio.data.entities

import com.example.csgocaseswatcherapp.screens.portfolio.view.entities.PortfolioCaseItem

object PortfolioItemDtoMapper {

    fun map(portfolioItemDto: PortfolioItemDto): PortfolioCaseItem {
        return PortfolioCaseItem(
            caseImage = portfolioItemDto.imageUrl,
            caseName = portfolioItemDto.name,
            caseAmount = portfolioItemDto.amount,
            casePrice = portfolioItemDto.purchasePrice,
            caseOverallValue = portfolioItemDto.overallValue,
            caseProfitLoss = portfolioItemDto.profitLoss
        )
    }
}