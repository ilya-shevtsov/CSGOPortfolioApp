package com.example.csgocaseswatcherapp.features.addcase.domain.usecases

import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseError
import com.example.csgocaseswatcherapp.features.addcase.domain.AmountValidationResult
import javax.inject.Inject

class ValidateCaseAmountUseCase @Inject constructor() {

    operator fun invoke(raw: String): AmountValidationResult {
        val amount = raw.toIntOrNull()

        return when {
            raw.isBlank() -> AmountValidationResult.Fail(AddCaseError.AMOUNT_EMPTY)
            amount == null -> AmountValidationResult.Fail(AddCaseError.AMOUNT_NOT_INT)
            amount <= 0 -> AmountValidationResult.Fail(AddCaseError.AMOUNT_NOT_POSITIVE)
            else -> AmountValidationResult.Success(amount)
        }
    }
}