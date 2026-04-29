package com.example.csgocaseswatcherapp.features.addcase.domain


sealed interface AmountValidationResult {
    data class Success(val amount: Int) : AmountValidationResult
    data class Fail(val error: AddCaseError) : AmountValidationResult
}
