package com.example.csgocaseswatcherapp.features.addcasefragment.domain

import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseError

sealed interface PriceValidationResult {
    data class Success(val price: Double) : PriceValidationResult
    data class Fail(val error: AddCaseError) : PriceValidationResult
}