package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingEntry
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.toText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortingModalViewModel @Inject constructor() : ViewModel() {


    val uiState: MutableStateFlow<SortingModalViewState> = MutableStateFlow(value = initState())

    private fun initState(): SortingModalViewState {
        return SortingModalViewState(sortingEntryList = SortingMethod.entries.map { sortingMethod ->
            SortingEntry(name = sortingMethod.toText(), method = sortingMethod)
        })
    }

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