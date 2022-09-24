package com.example.csgocaseswatcherapp.core.di

import com.example.csgocaseswatcherapp.core.di.module.AppModule
import com.example.csgocaseswatcherapp.presentation.screens.caseanalytics.view.CaseAnalyticsFragment
import com.example.csgocaseswatcherapp.presentation.screens.caseanalyticsdetails.view.CaseAnalyticsDetailsFragment
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.CaseOverviewFragment
import com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view.CaseDetailsFragment
import com.example.csgocaseswatcherapp.presentation.screens.portfolio.view.PortfolioFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: CaseOverviewFragment)

    fun inject(fragment: CaseDetailsFragment)

    fun inject(fragment: CaseAnalyticsFragment)

    fun inject(fragment: CaseAnalyticsDetailsFragment)

    fun inject(fragment: PortfolioFragment)
}