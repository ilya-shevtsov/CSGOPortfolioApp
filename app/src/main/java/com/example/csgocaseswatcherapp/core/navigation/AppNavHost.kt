package com.example.csgocaseswatcherapp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverViewRoute
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.start.view.StartRoute
import com.example.csgocaseswatcherapp.features.start.view.StartViewModel
import kotlinx.serialization.Serializable

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
            composable<Screen.Start> {
                val viewModel: StartViewModel = viewModel()
                StartRoute(
                    viewModel = viewModel,
                    onNavigate = {screen -> navController.navigate(screen)}
                )
            }
            composable<Screen.CaseOverView> {
                val viewModel: CaseOverviewViewModel = viewModel()
                CaseOverViewRoute(
                    viewModel = viewModel,
                    onNavigateToDetails = {caseOverViewModel -> navController.navigate(Screen.CaseOverViewDetails(caseOverviewModel = caseOverViewModel))}
                )
            }
        }
    }
}

sealed class Screen {
    @Serializable
    data class Start(val preferredCurrency: String?) : Screen()

    @Serializable
    data object CaseOverView : Screen()

    @Serializable
    data class CaseOverViewDetails(val caseOverviewModel: CaseOverviewModel) : Screen()

    @Serializable
    data object CaseAnalytics : Screen()

    @Serializable
    data object CurrencyChange : Screen()

    @Serializable
    data object Portfolio : Screen()

//    @Serializable
//    data class PortfolioDetails(val portfolioItemList: List<PortfolioItem>) : Screen()

    @Serializable
    data object AddCase : Screen()
}
