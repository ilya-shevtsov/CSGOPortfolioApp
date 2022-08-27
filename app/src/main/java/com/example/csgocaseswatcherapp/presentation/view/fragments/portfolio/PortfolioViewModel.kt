package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    fun handleAction(action: PortfolioViewAction) {
        when (action) {
            is PortfolioViewAction.OnAddCaseClicked -> handleOnAddCaseClicked()
        }
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

    private fun createInitialState(): PortfolioViewState {
        return PortfolioViewState(
            listOf(
                PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Chroma%20Case",
                    caseName = "Chroma Case",
                    caseAmount = 4,
                    casePrice = 20.0,
                    caseOverallValue = 500.0,
                    caseProfitLoss = 500.0
                )
            )
        )
    }
}