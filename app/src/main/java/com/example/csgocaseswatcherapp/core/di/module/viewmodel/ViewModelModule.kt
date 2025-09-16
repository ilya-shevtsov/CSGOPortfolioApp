package com.example.csgocaseswatcherapp.core.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseViewModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseDetailsViewModel
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioViewModel
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsViewModel
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingModalViewModel
import com.example.csgocaseswatcherapp.features.start.view.StartViewModel
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
    @ViewModelKey(PortfolioViewModel::class)
    fun PortfolioViewModel(viewModel: PortfolioViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCaseViewModel::class)
    fun AddCaseViewModel(viewModel: AddCaseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun StartViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SortingModalViewModel::class)
    fun SortingBottomSheetFragmentViewModel(viewModel: SortingModalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PortfolioDetailsViewModel::class)
    fun PortfolioDetailsViewModel(viewModel: PortfolioDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyChangeViewModel::class)
    fun CurrencyChangeViewModel(viewModel: CurrencyChangeViewModel): ViewModel
}
