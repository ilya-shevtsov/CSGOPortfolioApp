package com.example.csgocaseswatcherapp.features.addcase.domain

import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseError

sealed interface AmountValidationResult {
    data class Success(val amount: Int) : AmountValidationResult
    data class Fail(val error: AddCaseError) : AmountValidationResult
}
