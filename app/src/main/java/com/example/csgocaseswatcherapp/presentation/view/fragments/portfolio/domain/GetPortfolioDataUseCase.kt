package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem
import javax.inject.Inject

class GetPortfolioDataUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository
) {

    suspend operator fun invoke(): List<PortfolioCaseItem> {
        return portfolioRepository.getPortfolioData()
    }
}