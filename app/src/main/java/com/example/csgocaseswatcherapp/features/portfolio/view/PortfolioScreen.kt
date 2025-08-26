package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.compose.runtime.Composable
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.facebook.common.internal.ImmutableList

@Composable
fun PortfolioScreen(
    state: PortfolioViewState,
    onDetailsClicked: () -> Unit,
    onAddCaseClicked: () -> Unit,
    onSortingClicked: () -> Unit,
) {

    when (state) {
        is PortfolioViewState.Loading -> LoadingScreen()
        is PortfolioViewState.Error -> ErrorScreen()
        is PortfolioViewState.Content -> PortfolioContent()
    }
}



@Composable
fun PortfolioContent(){

}

