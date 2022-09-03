package com.example.csgocaseswatcherapp.data.model.portfolioitem

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem

object PortfolioItemDtoMapper {

    fun map(portfolioItemDto: PortfolioItemDto): PortfolioItem {
        return PortfolioItem(
            caseImage = portfolioItemDto.imageUrl,
            caseName = portfolioItemDto.name,
            caseAmount = portfolioItemDto.amount,
            casePrice = portfolioItemDto.purchasePrice,
            caseOverallValue = portfolioItemDto.overallValue,
            caseProfitLoss = portfolioItemDto.profitLoss
        )
    }
}