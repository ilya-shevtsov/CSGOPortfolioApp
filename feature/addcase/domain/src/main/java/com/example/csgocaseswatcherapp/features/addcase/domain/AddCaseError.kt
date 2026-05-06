package com.example.csgocaseswatcherapp.features.addcase.domain

import androidx.annotation.StringRes

enum class AddCaseError(@StringRes val resId: Int) {
    NAME_EMPTY(R.string.error_choose_case),
    NAME_UNKNOWN(R.string.error_unknown_case),

    AMOUNT_EMPTY(R.string.error_enter_amount),
    AMOUNT_NOT_INT(R.string.error_amount_integer),
    AMOUNT_NOT_POSITIVE(R.string.error_amount_positive),

    PRICE_EMPTY(R.string.error_enter_price),
    PRICE_DANGLING_DECIMAL(R.string.error_finish_decimal),
    PRICE_NOT_NUMBER(R.string.error_price_number),
    PRICE_NOT_POSITIVE(R.string.error_price_positive),

    GENERAL_VALIDATION_ERROR(R.string.add_case_validation_error)
}