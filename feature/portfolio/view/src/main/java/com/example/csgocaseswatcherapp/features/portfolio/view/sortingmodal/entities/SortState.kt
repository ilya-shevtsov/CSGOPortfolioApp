package com.example.csgocaseswatcherapp.features.portfolio.view.sortingmodal.entities

import androidx.annotation.StringRes
import com.example.csgocaseswatcherapp.features.portfolio.R

enum class SortState(@get:StringRes val labelResId: Int) {
    NAME(R.string.sort_state_name),
    AMOUNT(R.string.sort_state_amount),
    PRICE(R.string.sort_state_price),
    OVERALL_VALUE(R.string.sort_state_overall_value),
    PROFIT_LOSS(R.string.sort_state_profit_loss);

}

