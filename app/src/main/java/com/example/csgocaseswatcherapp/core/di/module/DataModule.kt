package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics.data.CaseAnalyticsServerRepository
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.data.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.domain.repository.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.data.PortfolioServerRepository
import com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio.domain.PortfolioRepository
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