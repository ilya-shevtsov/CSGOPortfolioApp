package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioGroupieItem
import com.example.csgocaseswatcherapp.presentation.model.portfoliodetailsvalueitem.PortfolioValueItem
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioDetailsViewModel : ViewModel() {}
//
//
//
//    val uiState: MutableStateFlow<PortfolioDetailsViewState> =
//        MutableStateFlow(value = PortfolioDetailsViewState.Loading)
//
//    val uiEvent = MutableSharedFlow<PortfolioDetailsViewEvent>()
//
//    init {
//        viewModelScope.launch {
//            try {
//                showContent(portfolioValueList)
//            } catch (throwable: Throwable) {
//                showError()
//                Log.e("Logging_getCaseList", "${throwable.message}")
//            }
//        }
//    }
//
//    private fun showError() {
//        uiState.value = PortfolioDetailsViewState.Error
//    }
//
//    private fun mapToPieEntry(PortfolioItemList: List<PortfolioGroupieItem>): List<PieEntry> {
//        return PortfolioItemList.map { case ->
//            PieEntry(
//                case.caseAmount.toFloat(),
//                case.caseName
//                    .replace("Operation", "")
//                    .replace("Case", "")
//            )
//        }
//    }
//
//    private fun showContent(portfolioValueList: List<PortfolioValueItem>) {
//        uiState.value = PortfolioDetailsViewState.Content(portfolioValueList,mapToBarEntry(portfolioValueList))
//    }
//}
//
