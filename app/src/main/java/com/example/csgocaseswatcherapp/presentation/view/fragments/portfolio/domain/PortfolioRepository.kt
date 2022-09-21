package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioCaseItem

interface PortfolioRepository {

    suspend fun getPortfolioData(): List<PortfolioCaseItem>

}