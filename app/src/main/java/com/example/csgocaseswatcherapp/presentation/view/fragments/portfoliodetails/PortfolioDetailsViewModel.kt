package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioDetailsViewModel : ViewModel() {

    var portfolioValueList: List<PortfolioValueItem> = listOf()

    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
        MutableStateFlow(value = PortfolioDetailsViewState.Loading)

    val uiEvent = MutableSharedFlow<PortfolioDetailsViewEvent>()

    init {
        viewModelScope.launch {
            try {
                showContent(portfolioValueList)
            } catch (throwable: Throwable) {
                showError()
                Log.e("Logging_getCaseList", "${throwable.message}")
            }
        }
    }

    private fun showError() {
        uiState.value = PortfolioDetailsViewState.Error
    }

    private fun mapToBarEntry(portfolioValueList: List<PortfolioValueItem>): List<BarEntry> {
        return portfolioValueList.map { value ->
            BarEntry(value.date, value.value)
        }
    }

    private fun showContent(portfolioValueList: List<PortfolioValueItem>) {
        uiState.value = PortfolioDetailsViewState.Content(portfolioValueList,mapToBarEntry(portfolioValueList))
    }
}

