package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.data.repository.CaseAnalyticsRepositoryImpl
import com.example.csgocaseswatcherapp.data.repository.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.domain.repository.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.domain.repository.CaseRepository
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
    fun caseAnalyticsRepository(impl: CaseAnalyticsRepositoryImpl): CaseAnalyticsRepository

    @Binds
    @Singleton
    fun portfolioServerRepository(impl: PortfolioServerRepository): PortfolioRepository
}