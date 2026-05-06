package com.example.csgocaseswatcherapp.features.addcase.domain.usecases

import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseError
import com.example.csgocaseswatcherapp.features.addcase.domain.PriceValidationResult
import javax.inject.Inject

class ValidateCasePriceUseCase @Inject constructor() {

    operator fun invoke(raw: String): PriceValidationResult {
        val normalized = raw.replace(',', '.')
        val price = normalized.toDoubleOrNull()
        val hasDanglingDecimalSeparator = raw.endsWith('.') || raw.endsWith(',')
        return when {
            raw.isBlank() -> PriceValidationResult.Fail(AddCaseError.PRICE_EMPTY)
            hasDanglingDecimalSeparator -> PriceValidationResult.Fail(AddCaseError.PRICE_DANGLING_DECIMAL)
            price == null -> PriceValidationResult.Fail(AddCaseError.PRICE_NOT_NUMBER)
            price <= 0.0 -> PriceValidationResult.Fail(AddCaseError.PRICE_NOT_POSITIVE)
            else -> PriceValidationResult.Success(price)
        }
    }
}