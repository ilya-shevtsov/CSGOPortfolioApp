package com.example.csgocaseswatcherapp.features.portfolio.data

import com.example.csgocaseswatcherapp.api.ServerApi
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import javax.inject.Inject

class PortfolioServerRepository @Inject constructor(
    private val api: ServerApi
) : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioItem> {
        val responseDto = api.getPortfolioData()
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }
}
