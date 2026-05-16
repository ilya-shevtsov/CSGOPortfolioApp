package com.example.csgocaseswatcherapp.features.portfolio.view.sorting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.SortingEntry
import com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model.labelResId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SortingModalViewModel @Inject constructor() : ViewModel() {


    val uiState: MutableStateFlow<SortingModalViewState> = MutableStateFlow(value = initState())

    private fun initState(): SortingModalViewState {
        return SortingModalViewState(sortingEntryList = PortfolioSortType.entries.map { sortType ->
            SortingEntry(resId = sortType.labelResId, sortType = sortType)
        })
    }

    val uiEvent = MutableSharedFlow<SortingModalEvent>()

    fun handleAction(action: SortingModalAction) {
        when (action) {
            is SortingModalAction.OnSortingMethodSelected -> handleOnSortingMethodSelected(
                action.sortType
            )
        }
    }

    private fun handleOnSortingMethodSelected(sortType: PortfolioSortType) {
        viewModelScope.launch {
            uiEvent.emit(
                SortingModalEvent.NavigateToPortfolioWithSelectedSortingMethod(
                    sortType
                )
            )
        }
    }
}