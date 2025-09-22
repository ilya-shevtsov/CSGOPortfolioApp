package com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases

import com.example.csgocaseswatcherapp.features.addcasefragment.data.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import javax.inject.Inject

class GetCaseSuggestionListUseCase @Inject constructor(
    private val addCaseRepository: AddCaseRepository
) {
    operator fun invoke(): List<AddCaseSuggestion> {
        return addCaseRepository.getSuggestionList()
    }
}