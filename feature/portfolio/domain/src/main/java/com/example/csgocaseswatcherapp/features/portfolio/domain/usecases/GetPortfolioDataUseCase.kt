package com.example.csgocaseswatcherapp.features.portfolio.domain.usecases

import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import javax.inject.Inject

class GetPortfolioDataUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) {

    suspend operator fun invoke(): List<PortfolioItem> {
        return portfolioRepository.getPortfolioData()
    }
}
