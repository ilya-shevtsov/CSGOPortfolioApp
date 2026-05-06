package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SortingBottomModal(
    viewModel: SortingModalViewModel,
    onDismissRequest: () -> Unit,
    onSortingSelected: (PortfolioSortType) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod -> {
                    onSortingSelected(event.sortType)
                    onDismissRequest()
                }
            }
        }
    }

    SortingScreen(
        state = state,
        onClick = { method ->
            viewModel.handleAction(
                SortingModalAction.OnSortingMethodSelected(method)
            )
        }
    )
}