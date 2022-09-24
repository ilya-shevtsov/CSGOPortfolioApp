package com.example.csgocaseswatcherapp.presentation.screens.portfolio.data

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.entities.PortfolioCaseItem
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.data.entities.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.domain.PortfolioRepository
import javax.inject.Inject

class PortfolioServerRepository @Inject constructor() : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioCaseItem> {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }

}