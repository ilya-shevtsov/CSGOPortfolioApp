package com.example.csgocaseswatcherapp.features.portfolio.data

import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDto
import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import javax.inject.Inject

class LocalPortfolioServerRepository @Inject constructor() : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioItem> {
        val responseDto = mockServerResponse
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }

    private val mockServerResponse = listOf(
        PortfolioItemDto(
            name = "Chroma 3 Case",
            amount = 20,
            purchasePrice = 2.0,
            overallValue = 40.0,
            profitLoss = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%203%20Case"
        ),
        PortfolioItemDto(
            name = "eSports 2013 Case",
            amount = 1,
            purchasePrice = 6.0,
            overallValue = 6.0,
            profitLoss = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Case"
        ),
        PortfolioItemDto(
            name = "Chroma Case",
            amount = 2,
            purchasePrice = 3.0,
            overallValue = 6.0,
            profitLoss = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
        ),
        PortfolioItemDto(
            name = "Operation Bravo Case",
            amount = 3,
            purchasePrice = 2.0,
            overallValue = 6.0,
            profitLoss = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Bravo%20Case"
        )
    )
}
