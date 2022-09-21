package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain

import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.portfolioitem.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import javax.inject.Inject

class GetPortfolioDataUseCase @Inject constructor() {

    suspend operator fun invoke(): List<PortfolioCaseItem> {
        val responseDto = ApiTools.getApiService().getPortfolioData()
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }
}