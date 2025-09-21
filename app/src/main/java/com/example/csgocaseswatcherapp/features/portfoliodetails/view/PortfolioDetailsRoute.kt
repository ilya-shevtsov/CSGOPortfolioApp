package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem

@Composable
fun PortfolioDetailsRoute(
    viewModel: PortfolioDetailsViewModel,
    portfolioItemList: List<PortfolioItem>
) {

    LaunchedEffect(portfolioItemList) {
        viewModel.handleAction(PortfolioDetailsAction.OnPortfolioDataProvided(portfolioItemList))
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PortfolioDetailsScreen(
        state = state,
    )
}