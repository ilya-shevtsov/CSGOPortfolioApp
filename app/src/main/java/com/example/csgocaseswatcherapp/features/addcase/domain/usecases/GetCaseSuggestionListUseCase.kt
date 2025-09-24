package com.example.csgocaseswatcherapp.features.addcase.domain.usecases

import com.example.csgocaseswatcherapp.features.addcase.data.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseRepository
import javax.inject.Inject

class GetCaseSuggestionListUseCase @Inject constructor(
    private val addCaseRepository: AddCaseRepository
) {
    operator fun invoke(): List<AddCaseSuggestion> {
        return addCaseRepository.getSuggestionList()
    }
}