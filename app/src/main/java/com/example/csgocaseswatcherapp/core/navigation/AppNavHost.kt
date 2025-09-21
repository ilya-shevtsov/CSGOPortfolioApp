package com.example.csgocaseswatcherapp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseRoute
import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseViewModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsRoute
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsScreen
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverViewRoute
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseDetailsViewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseOverviewDetailsRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioRoute
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioScreen
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsRoute
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsViewModel
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingModalViewModel
import com.example.csgocaseswatcherapp.features.start.view.StartRoute
import com.example.csgocaseswatcherapp.features.start.view.StartViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)

    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Start::class
        ) {
            composable<Screen.Start> { entry ->
                val viewModel: StartViewModel = hiltViewModel()
                val args = entry.toRoute<Screen.Start>()
                StartRoute(
                    viewModel = viewModel,
                    onNavigate = { screen -> navController.navigate(screen) },
                    currency = args.preferredCurrency
                )
            }
            composable<Screen.CaseOverView> {
                val viewModel: CaseOverviewViewModel = hiltViewModel()
                CaseOverViewRoute(
                    viewModel = viewModel,
                    onNavigateToDetails = { caseOverViewModel ->
                        navController.navigate(
                            Screen.CaseDetails(
                                caseOverviewModel = caseOverViewModel
                            )
                        )
                    }
                )
            }
            composable<Screen.CaseDetails>(
                typeMap = mapOf(
                    typeOf<CaseOverviewModel>() to CustomNavType.CaseOverviewModelType
                )
            ) { entry ->
                val viewModel: CaseDetailsViewModel = hiltViewModel()
                val args = entry.toRoute<Screen.CaseDetails>()
                CaseOverviewDetailsRoute(
                    viewModel = viewModel,
                    currentCase = args.caseOverviewModel
                )
            }
            composable<Screen.CaseAnalytics> {
                val viewModel: CaseAnalyticsViewModel = hiltViewModel()
                CaseAnalyticsRoute(
                    viewModel = viewModel
                )
            }
            composable<Screen.Portfolio> {
                val viewModel: PortfolioViewModel = hiltViewModel()
                val sortingViewModel: SortingModalViewModel = hiltViewModel()
                PortfolioRoute(
                    viewModel = viewModel,
                    sortingViewModel = sortingViewModel,
                    onNavigateToAddCase = { screen -> navController.navigate(screen) },
                    onNavigateToPortfolioDetails = { portfolioItemList ->
                        navController.navigate(
                            Screen.PortfolioDetails(portfolioItemList = portfolioItemList)
                        )
                    }
                )
            }
            composable<Screen.PortfolioDetails>(
                typeMap = mapOf(
                    typeOf<List<PortfolioItem>>() to CustomNavType.PortfolioItemListType
                )
            ) { entry ->
                val viewModel: PortfolioDetailsViewModel = hiltViewModel()
                val args = entry.toRoute<Screen.PortfolioDetails>()
                PortfolioDetailsRoute(
                    viewModel = viewModel,
                    portfolioItemList = args.portfolioItemList
                )
            }
            composable<Screen.AddCase> {
                val viewModel: AddCaseViewModel = hiltViewModel()
                AddCaseRoute(
                    viewModel = viewModel,
                    navigateToPortfolio = { screen -> navController.navigate(screen) }
                )
            }
            composable<Screen.CurrencyChange> {
                val viewModel: CurrencyChangeViewModel = hiltViewModel()
                CurrencyChangeRoute(
                    viewModel = viewModel,
                    navigateToStartWithPreferredCurrency = { currency ->
                        navController.navigate(
                            Screen.Start(preferredCurrency = currency)
                        )
                    }
                )
            }
        }
    }
}

sealed class Screen {
    @Serializable
    data class Start(val preferredCurrency: String? = null) : Screen()

    @Serializable
    data object CaseOverView : Screen()

    @Serializable
    data class CaseDetails(val caseOverviewModel: CaseOverviewModel) : Screen()

    @Serializable
    data object CaseAnalytics : Screen()

    @Serializable
    data object CurrencyChange : Screen()

    @Serializable
    data object Portfolio : Screen()

    @Serializable
    data class PortfolioDetails(val portfolioItemList: List<PortfolioItem>) : Screen()

    @Serializable
    data object AddCase : Screen()
}
