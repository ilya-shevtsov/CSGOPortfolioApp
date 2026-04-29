package com.example.csgocaseswatcherapp.data.local

import com.example.csgocaseswatcherapp.features.portfolio.data.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import javax.inject.Inject

class LocalPortfolioServerRepository @Inject constructor(
    private val store: LocalMockStore
) : PortfolioRepository {

    override suspend fun getPortfolioData(): List<PortfolioItem> {
        val responseDto = store.portfolio.value
        return responseDto.map { caseDto ->
            PortfolioItemDtoMapper.map(caseDto)
        }
    }
}