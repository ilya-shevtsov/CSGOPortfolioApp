package com.example.csgocaseswatcherapp.features.portfolio.data.entities

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem

object PortfolioItemDtoMapper {

    fun map(portfolioItemDto: PortfolioItemDto): PortfolioItem {
        return PortfolioItem(
            image = portfolioItemDto.imageUrl,
            name = portfolioItemDto.name,
            amount = portfolioItemDto.amount,
            price = portfolioItemDto.purchasePrice,
            overallValue = portfolioItemDto.overallValue,
            profitLoss = portfolioItemDto.profitLoss
        )
    }
}
