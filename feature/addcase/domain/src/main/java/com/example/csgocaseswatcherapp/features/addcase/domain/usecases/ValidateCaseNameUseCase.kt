package com.example.csgocaseswatcherapp.features.addcase.domain.usecases

import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseError
import javax.inject.Inject

class ValidateCaseNameUseCase @Inject constructor() {
    operator fun invoke(
        name: String,
        allowedNames: Set<String>
    ): AddCaseError? {
        return when {
            name.isBlank() -> AddCaseError.NAME_EMPTY
            name !in allowedNames -> AddCaseError.NAME_UNKNOWN
            else -> null
        }
    }
}