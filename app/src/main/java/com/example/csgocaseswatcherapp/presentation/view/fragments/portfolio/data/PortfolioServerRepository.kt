package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.data

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.portfolioitem.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain.PortfolioRepository
import javax.inject.Inject

class PortfolioServerRepository @Inject constructor() : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioCaseItem> {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }

}