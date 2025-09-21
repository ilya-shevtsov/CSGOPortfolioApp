@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.navigation.Destination
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingBottomModal
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingModalViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PortfolioRoute(
    viewModel: PortfolioViewModel,
    sortingViewModel: SortingModalViewModel,
    onNavigateToAddCase: (Destination.AddCase) -> Unit,
    onNavigateToPortfolioDetails: (List<PortfolioItem>) -> Unit
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.handleAction(PortfolioViewAction.OnCreate)
    }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is PortfolioViewEvent.NavigateToAddCase -> onNavigateToAddCase(Destination.AddCase)
                is PortfolioViewEvent.NavigateToPortfolioDetails -> onNavigateToPortfolioDetails(
                    event.portfolioItemListArgs.portfolioItemList
                )
                is PortfolioViewEvent.ScrollToTop -> {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }

    PortfolioScreen(
        state = state,
        onAction = { action -> viewModel.handleAction(action = action) },
        listState = listState
    )

    val isVisible = (state as? PortfolioViewState.Content)?.isSortingSheetVisible == true
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.handleAction(PortfolioViewAction.HideSortingModal) },
            sheetState = sheetState
        ) {
            SortingBottomModal(
                viewModel = sortingViewModel,
                onDismissRequest = { viewModel.handleAction(PortfolioViewAction.HideSortingModal) },
                onSortingSelected = { sortingMethod ->
                    viewModel.handleAction(
                        PortfolioViewAction.OnSortingMethodSelected(sortingMethod)
                    )
                }
            )
        }
    }
}