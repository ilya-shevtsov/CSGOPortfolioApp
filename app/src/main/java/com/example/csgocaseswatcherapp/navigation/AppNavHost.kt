package com.example.csgocaseswatcherapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.csgocaseswatcherapp.core.ui.adaptive.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.adaptive.rememberDeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.core.ui.topbar.AppTopBar
import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseRoute
import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseViewModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsRoute
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverViewRoute
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.model.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseDetailsViewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseOverviewDetailsRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeViewModel
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioRoute
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.SortingModalViewModel
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsRoute
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsViewModel
import com.example.csgocaseswatcherapp.features.start.view.StartRoute
import com.example.csgocaseswatcherapp.features.start.view.StartViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    val isStartDestination = currentDestination
        ?.hierarchy
        ?.any { it.hasRoute<Destination.Start>() } == true


    val title = remember(backStackEntry) {
        computeTitle(backStackEntry)
    }

    val canNavigateBack =
        navController.previousBackStackEntry != null && !isStartDestination

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )

    val deviceConfigurationType = rememberDeviceConfigurationType()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = title,
                canNavigateBack = canNavigateBack,
                onBack = { navController.popBackStack() },
                isCompact = deviceConfigurationType == DeviceConfigurationType.MOBILE_LANDSCAPE,
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.safeContent,
        containerColor = AppTheme.colors.background
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destination.Start::class,
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(paddingValues),
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            composable<Destination.Start> {
                val viewModel: StartViewModel = hiltViewModel()
                StartRoute(
                    viewModel = viewModel,
                    onNavigateToCaseOverview = {
                        navController.navigate(Destination.CaseOverView)
                    },
                    onNavigateToCaseAnalytics = {
                        navController.navigate(Destination.CaseAnalytics)
                    },
                    onNavigateToPortfolio = {
                        navController.navigate(Destination.Portfolio)
                    },
                    onNavigateToCurrencyChange = {
                        navController.navigate(Destination.CurrencyChange)
                    }
                )
            }
            composable<Destination.CaseOverView> {
                val viewModel: CaseOverviewViewModel = hiltViewModel()
                CaseOverViewRoute(
                    viewModel = viewModel,
                    onNavigateToDetails = { caseOverViewModel ->
                        navController.navigate(
                            Destination.CaseDetails(
                                caseOverviewModel = caseOverViewModel
                            )
                        )
                    }
                )
            }
            composable<Destination.CaseDetails>(
                typeMap = mapOf(
                    typeOf<CaseOverviewModel>() to CustomNavType.CaseOverviewModelType
                )
            ) { entry ->
                val viewModel: CaseDetailsViewModel = hiltViewModel(entry)
                val args = entry.toRoute<Destination.CaseDetails>()
                CaseOverviewDetailsRoute(
                    viewModel = viewModel,
                    currentCase = args.caseOverviewModel
                )
            }
            composable<Destination.CaseAnalytics> {
                val viewModel: CaseAnalyticsViewModel = hiltViewModel()
                CaseAnalyticsRoute(viewModel = viewModel)
            }
            composable<Destination.Portfolio> {
                val viewModel: PortfolioViewModel = hiltViewModel()
                val sortingViewModel: SortingModalViewModel = hiltViewModel()
                PortfolioRoute(
                    viewModel = viewModel,
                    sortingViewModel = sortingViewModel,
                    onNavigateToAddCase = { navController.navigate(Destination.AddCase) },
                    onNavigateToPortfolioDetails = { portfolioItemList ->
                        navController.navigate(
                            Destination.PortfolioDetails(portfolioItemList = portfolioItemList)
                        )
                    }
                )
            }
            composable<Destination.PortfolioDetails>(
                typeMap = mapOf(
                    typeOf<List<PortfolioItem>>() to CustomNavType.PortfolioItemListType
                )
            ) { entry ->
                val viewModel: PortfolioDetailsViewModel = hiltViewModel(entry)
                val args = entry.toRoute<Destination.PortfolioDetails>()
                PortfolioDetailsRoute(
                    viewModel = viewModel,
                    portfolioItemList = args.portfolioItemList
                )
            }
            composable<Destination.AddCase> {
                val viewModel: AddCaseViewModel = hiltViewModel()
                AddCaseRoute(
                    viewModel = viewModel,
                    navigateToPortfolio = {
                        navController.navigate(Destination.Portfolio) {
                            popUpTo(Destination.Portfolio::class) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Destination.CurrencyChange> {
                val viewModel: CurrencyChangeViewModel = hiltViewModel()
                CurrencyChangeRoute(
                    viewModel = viewModel,
                    navigateToStart = { preferredCurrency ->
                        navController.navigate(Destination.Start(preferredCurrency = preferredCurrency)) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun computeTitle(entry: NavBackStackEntry?): String {
    val route = entry?.destination?.route ?: return ""
    return when {
        route.startsWith(requireNotNull(Destination.Start::class.qualifiedName)) -> "Welcome"
        route.startsWith(requireNotNull(Destination.CaseOverView::class.qualifiedName)) -> "Case Overview"
        route.startsWith(requireNotNull(Destination.CaseDetails::class.qualifiedName)) -> "Case Details"
        route.startsWith(requireNotNull(Destination.CaseAnalytics::class.qualifiedName)) -> "Case Analytics"
        route.startsWith(requireNotNull(Destination.Portfolio::class.qualifiedName)) -> "Portfolio"
        route.startsWith(requireNotNull(Destination.PortfolioDetails::class.qualifiedName)) -> "Portfolio Details"
        route.startsWith(requireNotNull(Destination.AddCase::class.qualifiedName)) -> "Add Case"
        route.startsWith(requireNotNull(Destination.CurrencyChange::class.qualifiedName)) -> "Currency"
        else -> ""
    }
}

sealed class Destination {
    @Serializable
    data class Start(val preferredCurrency: String? = null) : Destination()

    @Serializable
    data object CaseOverView : Destination()

    @Serializable
    data class CaseDetails(val caseOverviewModel: CaseOverviewModel) : Destination()

    @Serializable
    data object CaseAnalytics : Destination()

    @Serializable
    data object CurrencyChange : Destination()

    @Serializable
    data object Portfolio : Destination()

    @Serializable
    data class PortfolioDetails(val portfolioItemList: List<PortfolioItem>) : Destination()

    @Serializable
    data object AddCase : Destination()
}
