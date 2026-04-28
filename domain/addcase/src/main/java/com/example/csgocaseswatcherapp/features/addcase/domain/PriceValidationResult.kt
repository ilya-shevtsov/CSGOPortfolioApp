package com.example.csgocaseswatcherapp.features.addcase.domain


sealed interface PriceValidationResult {
    data class Success(val price: Double) : PriceValidationResult
    data class Fail(val error: AddCaseError) : PriceValidationResult
}