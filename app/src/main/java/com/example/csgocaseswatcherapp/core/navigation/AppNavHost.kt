package com.example.csgocaseswatcherapp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseRoute
import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseViewModel
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsRoute
import com.example.csgocaseswatcherapp.features.caseanalytics.view.CaseAnalyticsViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverViewRoute
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseDetailsViewModel
import com.example.csgocaseswatcherapp.features.caseoverviewdetails.view.CaseOverviewDetailsRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeRoute
import com.example.csgocaseswatcherapp.features.currencychange.view.CurrencyChangeViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioRoute
import com.example.csgocaseswatcherapp.features.portfolio.view.PortfolioViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsRoute
import com.example.csgocaseswatcherapp.features.portfoliodetails.view.PortfolioDetailsViewModel
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingModalViewModel
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

    val title = remember(backStackEntry) { computeTitle(backStackEntry) }
    val canNavigateBack = navController.previousBackStackEntry != null
    val onBack: () -> Unit = { navController.popBackStack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title, color = AppTheme.colors.onSurface) },
                navigationIcon = {
                    if (canNavigateBack && !isOnStartDestination(navController)) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = AppTheme.colors.onSurface
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.surface,
                    titleContentColor = AppTheme.colors.onSurface,
                    navigationIconContentColor = AppTheme.colors.onSurface
                )
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.Start::class
            ) {
                composable<Destination.Start> {
                    val viewModel: StartViewModel = hiltViewModel()
                    StartRoute(
                        viewModel = viewModel,
                        onNavigate = { destination -> navController.navigate(destination) },
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
                        onNavigateToAddCase = { destination -> navController.navigate(destination) },
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
                        navigateToPortfolio = { destination ->
                            navController.navigate(destination) {
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
}

private fun isOnStartDestination(navController: NavHostController): Boolean {
    val startRoute = Destination.Start::class.qualifiedName
    return navController.currentDestination
        ?.hierarchy
        ?.any { it.route == startRoute } == true
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
