package com.example.csgocaseswatcherapp.screens.portfolio.domain

import com.example.csgocaseswatcherapp.screens.portfolio.view.entities.PortfolioCaseItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioCaseItem>

}