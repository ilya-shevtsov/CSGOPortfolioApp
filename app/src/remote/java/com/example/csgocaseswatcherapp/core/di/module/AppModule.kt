package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.data.remote.AddCaseServerRepository
import com.example.csgocaseswatcherapp.data.remote.CaseAnalyticsServerRepository
import com.example.csgocaseswatcherapp.data.remote.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.data.remote.PortfolioServerRepository
import com.example.csgocaseswatcherapp.data.remote.StartServerRepository
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

    @Binds
    @Singleton
    fun startServerRepository(impl: StartServerRepository): StartRepository
}