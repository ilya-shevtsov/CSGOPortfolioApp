package com.example.csgocaseswatcherapp.presentation.screens.portfolio.domain

import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.entities.PortfolioCaseItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioCaseItem>

}