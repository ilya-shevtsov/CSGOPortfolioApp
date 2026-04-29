package com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.entities.SortState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SortingBottomModal(
    viewModel: SortingModalViewModel,
    onDismissRequest: () -> Unit,
    onSortingSelected: (SortState) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod -> {
                    onSortingSelected(event.sortState)
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