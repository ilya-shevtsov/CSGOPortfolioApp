package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.screens.caseanalytics.data.CaseAnalyticsServerRepository
import com.example.csgocaseswatcherapp.screens.caseanalytics.domain.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.screens.caseoverview.data.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.screens.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.screens.portfolio.data.PortfolioServerRepository
import com.example.csgocaseswatcherapp.screens.portfolio.domain.PortfolioRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun caseRepository(impl: CaseOverviewServerRepository): CaseRepository

    @Binds
    @Singleton
    fun caseAnalyticsRepository(impl: CaseAnalyticsServerRepository): CaseAnalyticsRepository

    @Binds
    @Singleton
    fun portfolioServerRepository(impl: PortfolioServerRepository): PortfolioRepository
}