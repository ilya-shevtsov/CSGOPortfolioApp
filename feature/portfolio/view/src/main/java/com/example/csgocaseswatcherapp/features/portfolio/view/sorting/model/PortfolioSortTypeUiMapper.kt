package com.example.csgocaseswatcherapp.features.portfolio.view.sorting.model

import androidx.annotation.StringRes
import com.example.csgocaseswatcherapp.features.portfolio.R
import com.example.csgocaseswatcherapp.features.portfolio.domain.model.PortfolioSortType

@get:StringRes
val PortfolioSortType.labelResId: Int
    get() = when (this) {
        PortfolioSortType.NAME -> R.string.sort_state_name
        PortfolioSortType.AMOUNT -> R.string.sort_state_amount
        PortfolioSortType.PRICE -> R.string.sort_state_price
        PortfolioSortType.OVERALL_VALUE -> R.string.sort_state_overall_value
        PortfolioSortType.PROFIT_LOSS -> R.string.sort_state_profit_loss
    }

