package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.features.addcasefragment.data.AddCaseServerRepository
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.caseanalytics.data.CaseAnalyticsServerRepository
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.features.caseoverview.data.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.portfolio.data.PortfolioServerRepository
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
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

    @Binds
    @Singleton
    fun addCaseServerRepository(impl: AddCaseServerRepository): AddCaseRepository
}