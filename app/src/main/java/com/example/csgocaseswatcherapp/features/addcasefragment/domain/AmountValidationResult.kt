package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseError

sealed interface AmountValidationResult {
    data class Success(val amount: Int) : AmountValidationResult
    data class Fail(val error: AddCaseError) : AmountValidationResult
}
