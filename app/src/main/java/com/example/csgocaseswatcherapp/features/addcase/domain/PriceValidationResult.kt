package com.example.csgocaseswatcherapp.features.addcase.domain

import com.example.csgocaseswatcherapp.features.addcase.view.AddCaseError

sealed interface PriceValidationResult {
    data class Success(val price: Double) : PriceValidationResult
    data class Fail(val error: AddCaseError) : PriceValidationResult
}