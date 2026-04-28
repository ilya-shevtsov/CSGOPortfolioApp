package com.example.csgocaseswatcherapp.features.addcase.domain.usecases

import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import javax.inject.Inject

class GetCaseSuggestionListUseCase @Inject constructor(
    private val addCaseRepository: AddCaseRepository
) {
    operator fun invoke(): List<AddCaseSuggestion> {
        return addCaseRepository.getSuggestionList()
    }
}