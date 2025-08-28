package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SortingBottomModal(
    viewModel: SortingModalViewModel,
    onDismissRequest: () -> Unit,
    onSortingSelected: (SortingMethod) -> Unit
) {
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
        onClick = { method ->
            viewModel.handleAction(
                SortingModalAction.OnSortingMethodSelected(method)
            )
        }
    )
}