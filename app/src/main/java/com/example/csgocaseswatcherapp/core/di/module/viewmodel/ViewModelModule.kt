package com.example.csgocaseswatcherapp.core.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.CaseAnalyticsViewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view.CaseAnalyticsDetailsViewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view.CaseDetailsViewModel
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.PortfolioViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CaseOverviewViewModel::class)
    fun caseOverviewViewModel(viewModel: CaseOverviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CaseDetailsViewModel::class)
    fun caseDetailsViewModel(viewModel: CaseDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CaseAnalyticsViewModel::class)
    fun caseAnalyticsViewModel(viewModel: CaseAnalyticsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CaseAnalyticsDetailsViewModel::class)
    fun CaseAnalyticsDetailsViewModel(viewModel: CaseAnalyticsDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PortfolioViewModel::class)
    fun PortfolioViewModel(viewModel: PortfolioViewModel): ViewModel



}
