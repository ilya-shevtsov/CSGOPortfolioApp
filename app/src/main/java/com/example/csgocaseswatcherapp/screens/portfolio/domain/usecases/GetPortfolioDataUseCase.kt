package com.example.csgocaseswatcherapp.screens.portfolio.domain.usecases

import com.example.csgocaseswatcherapp.screens.portfolio.view.entities.PortfolioCaseItem
import com.example.csgocaseswatcherapp.screens.portfolio.domain.PortfolioRepository
import javax.inject.Inject

class GetPortfolioDataUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) {

    suspend operator fun invoke(): List<PortfolioCaseItem> {
        return portfolioRepository.getPortfolioData()
    }
}