@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.csgocaseswatcherapp.features.portfolio.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.ui.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.rememberDeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.portfolio.domain.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.ModalSideSheet
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.SortingModalViewModel
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.SortingScreenRoute
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PortfolioRoute(
    viewModel: PortfolioViewModel,
    sortingViewModel: SortingModalViewModel,
    onNavigateToAddCase: () -> Unit,
    onNavigateToPortfolioDetails: (List<PortfolioItem>) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val listState = rememberLazyListState()

    val deviceConfigurationType = rememberDeviceConfigurationType()

    LaunchedEffect(Unit) {
        viewModel.handleAction(PortfolioAction.OnCreate)
    }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is PortfolioEvent.NavigateToAddCase -> onNavigateToAddCase()
                is PortfolioEvent.NavigateToPortfolioDetails -> onNavigateToPortfolioDetails(
                    event.portfolioItemList
                )

                is PortfolioEvent.ScrollToTop -> {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }

    val isSortingVisible = (state as? PortfolioViewState.Content)?.isSortingSheetVisible == true

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PortfolioScreen(
            state = state,
            onAction = { action -> viewModel.handleAction(action = action) },
            listState = listState,
            deviceConfigurationType = deviceConfigurationType
        )

        when (deviceConfigurationType) {
            DeviceConfigurationType.MOBILE_PORTRAIT -> {
                if (isSortingVisible) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            viewModel.handleAction(PortfolioAction.HideSortingModal)
                        },
                        sheetState = sheetState,
                        containerColor = AppTheme.colors.background,
                        contentColor = AppTheme.colors.onBackground,
                        dragHandle = {
                            BottomSheetDefaults.DragHandle(
                                color = AppTheme.colors.onBackground.copy(alpha = 0.4f)
                            )
                        }
                    ) {
                        SortingContent(
                            sortingViewModel = sortingViewModel,
                            viewModel = viewModel
                        )
                    }
                }
            }

            DeviceConfigurationType.MOBILE_LANDSCAPE -> {
                ModalSideSheet(
                    visible = isSortingVisible,
                    onDismissRequest = {
                        viewModel.handleAction(PortfolioAction.HideSortingModal)
                    }
                ) {
                    SortingContent(
                        sortingViewModel = sortingViewModel,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun SortingContent(
    sortingViewModel: SortingModalViewModel,
    viewModel: PortfolioViewModel
) {
    SortingScreenRoute(
        viewModel = sortingViewModel,
        onDismissRequest = {
            viewModel.handleAction(PortfolioAction.HideSortingModal)
        },
        onSortingSelected = { sortingMethod ->
            viewModel.handleAction(
                PortfolioAction.OnSortingMethodSelected(sortingMethod)
            )
        }
    )
}