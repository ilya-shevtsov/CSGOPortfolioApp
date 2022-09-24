package com.example.csgocaseswatcherapp.presentation.screens.portfolio.domain

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioCaseItem>

}