package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingEntry
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortingModalViewModel @Inject constructor() : ViewModel() {


    val uiState: MutableStateFlow<SortingModalViewState> = MutableStateFlow(value = initState())

    private fun initState(): SortingModalViewState {
        return SortingModalViewState(sortingEntryList = SortState.entries.map { sortingMethod ->
            SortingEntry(name = sortingMethod.toText(), method = sortingMethod)
        })
    }

    val uiEvent = MutableSharedFlow<SortingModalEvent>()

    fun handleAction(action: SortingModalAction) {
        when (action) {
            is SortingModalAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(
                action.sortState
            )
        }
    }

    private fun handleOnSortingMethodSelected(sortState: SortState) {
        viewModelScope.launch {
            uiEvent.emit(
                SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod(
                    sortState
                )
            )
        }
    }

    private fun SortState.toText(): String {
        return name
            .replace(Regex("([a-z])([A-Z])"), "$1 $2")
            .lowercase()
            .split(" ")
            .joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
    }
}