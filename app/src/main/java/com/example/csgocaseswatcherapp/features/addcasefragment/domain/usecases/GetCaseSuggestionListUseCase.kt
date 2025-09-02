package com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases

import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import javax.inject.Inject

class GetCaseSuggestionListUseCase @Inject constructor(
    private val addCaseRepository: AddCaseRepository
) {
    operator fun invoke():List<String>{
        return addCaseRepository.getSuggestionList()
    }
}