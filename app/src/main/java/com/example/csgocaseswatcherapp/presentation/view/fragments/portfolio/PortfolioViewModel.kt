package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortfolioViewModel : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    fun handleAction(action: PortfolioViewAction) {
        when (action) {
            is PortfolioViewAction.OnAddCaseClicked -> handleOnAddCaseClicked()
            is PortfolioViewAction.OnCaseAdded -> handleOnCaseAdded(action)
            is PortfolioViewAction.OnCaseNameSortClicked -> handleOnCaseNameSortClicked()
            is PortfolioViewAction.OnCaseAmountClicked -> handleOnCaseAmountClicked()
            is PortfolioViewAction.OnCasePriceClicked -> handleOnCasePriceClicked()
            is PortfolioViewAction.OnCaseOverallValueClicked -> handleOnCaseOverallValueClicked()
            is PortfolioViewAction.OnCaseProfitLossClicked -> handleProfitLossClicked()
        }
    }

    private fun handleProfitLossClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseProfitLoss
        }
        uiState.value = PortfolioViewState(portfolioItem)
    }

    private fun handleOnCaseOverallValueClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseOverallValue
        }
        uiState.value = PortfolioViewState(portfolioItem)
    }

    private fun handleOnCasePriceClicked() {
        val portfolioItem = initialList.sortedBy { portfolioItem ->
            portfolioItem.casePrice
        }
        uiState.value = PortfolioViewState(portfolioItem)
    }

    private fun handleOnCaseAmountClicked() {
        val portfolioItem = initialList.sortedByDescending { portfolioItem ->
            portfolioItem.caseAmount
        }
        uiState.value = PortfolioViewState(portfolioItem)
    }

    private fun handleOnCaseNameSortClicked() {
        val portfolioItem = initialList.sortedBy { portfolioItem ->
            portfolioItem.caseName
        }
        uiState.value = PortfolioViewState(portfolioItem)
    }

    private fun handleOnCaseAdded(action: PortfolioViewAction.OnCaseAdded) {
        val portfolioItem = PortfolioItemMapper.map(action.addedCase)
        val portfolioItemList = listOf(portfolioItem) + uiState.value.portfolioItemList
        uiState.value = PortfolioViewState(portfolioItemList)
    }

    private fun handleOnAddCaseClicked() {
        viewModelScope.launch { uiEvent.emit(PortfolioViewEvent.NavigateToAddCase) }
    }

//PlaceHolder for cases in portfolio overview (later get from database)

    private val initialList = listOf(
        PortfolioItem(
            caseImage = "https://api.steamapis.com/image/item/730/Shadow%20Case",
            caseName = "Shadow Case",
            caseAmount = 4,
            casePrice = 20.0,
            caseOverallValue = 500.0,
            caseProfitLoss = 500.0
        ),
        PortfolioItem(
            caseImage = "https://api.steamapis.com/image/item/730/Prisma%20Case",
            caseName = "Prisma Case",
            caseAmount = 2,
            casePrice = 30.0,
            caseOverallValue = 100.0,
            caseProfitLoss = 100.0
        )
    )

    private fun createInitialState(): PortfolioViewState {
        return PortfolioViewState(
            listOf(
                PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Shadow%20Case",
                    caseName = "Shadow Case",
                    caseAmount = 4,
                    casePrice = 20.0,
                    caseOverallValue = 500.0,
                    caseProfitLoss = 500.0
                ),
                PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Prisma%20Case",
                    caseName = "Prisma Case",
                    caseAmount = 2,
                    casePrice = 30.0,
                    caseOverallValue = 100.0,
                    caseProfitLoss = 100.0
                )
            )
        )
    }

// This will be in createInitialState right after loading

    suspend fun getPortfolioData() {
        viewModelScope.launch {
            val response = ApiTools.getApiService().getPortfolioData()
            uiState.value = PortfolioViewState(response)
        }
    }
}