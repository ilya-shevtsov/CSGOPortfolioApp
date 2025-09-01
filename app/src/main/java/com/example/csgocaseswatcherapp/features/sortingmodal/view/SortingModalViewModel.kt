package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortingModalViewModel @Inject constructor() : ViewModel() {

    val uiEvent = MutableSharedFlow<SortingModalEvent>()

    fun handleAction(action: SortingModalAction) {
        when (action) {
            is SortingModalAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(
                action.sortingMethod
            )
        }
    }

    private fun handleOnSortingMethodSelected(sortingMethod: SortingMethod) {
        viewModelScope.launch {
            uiEvent.emit(
                SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod(
                    sortingMethod
                )
            )
        }
    }
}