package com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model

import androidx.annotation.StringRes
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType

data class SortingEntry(
    @StringRes val resId: Int,
    val sortType: PortfolioSortType
)
