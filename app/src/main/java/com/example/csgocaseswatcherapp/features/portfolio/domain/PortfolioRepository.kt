package com.example.csgocaseswatcherapp.features.portfolio.domain

import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioItem>

}
