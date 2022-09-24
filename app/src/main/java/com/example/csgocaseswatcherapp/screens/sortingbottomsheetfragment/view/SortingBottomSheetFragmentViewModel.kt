package com.example.csgocaseswatcherapp.screens.sortingbottomsheetfragment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SortingBottomSheetFragmentViewModel : ViewModel() {

    val uiEvent = MutableSharedFlow<SortingBottomSheetFragmentViewEvent>()

    fun handleAction(action: SortingBottomSheetFragmentViewAction) {
        when (action) {
            is SortingBottomSheetFragmentViewAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(
                action.sortingMethod
            )
        }
    }

    private fun handleOnSortingMethodSelected(sortingMethod: SortingMethod) {
        viewModelScope.launch {
            uiEvent.emit(
                SortingBottomSheetFragmentViewEvent.NavigateToPortfolioWithSelectedSortingMethod(
                    sortingMethod
                )
            )
        }
    }
}