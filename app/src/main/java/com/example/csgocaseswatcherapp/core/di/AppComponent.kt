package com.example.csgocaseswatcherapp.core.di

import com.example.csgocaseswatcherapp.core.di.module.AppModule
import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseFragment
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsFragment
import com.example.csgocaseswatcherapp.features.caseanalyticsdetails.view.CaseAnalyticsDetailsFragment
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewFragment
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseDetailsFragment
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioFragment
import com.example.csgocaseswatcherapp.features.start.view.StartFragment
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

    fun inject(fragment: AddCaseFragment)

    fun inject(fragment: StartFragment)
}