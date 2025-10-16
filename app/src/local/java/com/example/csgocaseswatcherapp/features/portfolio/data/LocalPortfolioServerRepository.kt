package com.example.csgocaseswatcherapp.features.portfolio.data

import com.LocalMockStore
import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDtoMapper
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
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
