package com.example.csgocaseswatcherapp.features.portfolio.domain

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioCaseItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioCaseItem>

}