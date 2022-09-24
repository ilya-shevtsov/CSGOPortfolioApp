package com.example.csgocaseswatcherapp.features.portfolio.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioCaseItem
import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import javax.inject.Inject

class PortfolioServerRepository @Inject constructor() : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioCaseItem> {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }

}