package com.example.csgocaseswatcherapp.core.di.module

import com.example.csgocaseswatcherapp.data.repository.CaseAnalyticsRepositoryImpl
import com.example.csgocaseswatcherapp.data.repository.CaseOverviewServerRepository
import com.example.csgocaseswatcherapp.domain.repository.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.domain.repository.CaseRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun caseRepository(overviewServer: CaseOverviewServerRepository): CaseRepository

    @Binds
    @Singleton
    fun caseAnalyticsRepository(impl: CaseAnalyticsRepositoryImpl): CaseAnalyticsRepository
}