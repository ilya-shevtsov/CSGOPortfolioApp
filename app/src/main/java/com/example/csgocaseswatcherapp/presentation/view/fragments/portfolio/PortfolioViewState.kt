package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem

sealed class PortfolioViewState {

    data class Content(
        val portfolioItemList: List<PortfolioItem>
    ) : PortfolioViewState()

    object Loading : PortfolioViewState()

    object Error : PortfolioViewState()
}

