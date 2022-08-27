package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class PortfolioViewModel : ViewModel() {

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<PortfolioViewEvent>()

    private fun createInitialState(): PortfolioViewState =
        PortfolioViewState(
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