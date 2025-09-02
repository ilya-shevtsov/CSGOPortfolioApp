package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SortingBottomModal(
    viewModel: SortingModalViewModel,
    onDismissRequest: () -> Unit,
    onSortingSelected: (SortingMethod) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod -> {
                    onSortingSelected(event.sortingMethod)
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