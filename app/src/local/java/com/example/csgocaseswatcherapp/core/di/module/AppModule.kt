package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.data.local.LocalAddCaseServerRepository
import com.example.csgocaseswatcherapp.data.local.LocalCaseAnalyticsServerRepository
import com.example.csgocaseswatcherapp.data.local.LocalCaseOverviewServerRepository
import com.example.csgocaseswatcherapp.data.local.LocalPortfolioServerRepository
import com.example.csgocaseswatcherapp.data.local.LocalStartServerRepository
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.portfolio.domain.PortfolioRepository
import com.example.csgocaseswatcherapp.features.start.domain.StartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun caseRepository(impl: LocalCaseOverviewServerRepository): CaseRepository

    @Binds
    @Singleton
    fun caseAnalyticsRepository(impl: LocalCaseAnalyticsServerRepository): CaseAnalyticsRepository

    @Binds
    @Singleton
    fun portfolioServerRepository(impl: LocalPortfolioServerRepository): PortfolioRepository

    @Binds
    @Singleton
    fun addCaseServerRepository(impl: LocalAddCaseServerRepository): AddCaseRepository

    @Binds
    @Singleton
    fun startServerRepository(impl: LocalStartServerRepository): StartRepository
}