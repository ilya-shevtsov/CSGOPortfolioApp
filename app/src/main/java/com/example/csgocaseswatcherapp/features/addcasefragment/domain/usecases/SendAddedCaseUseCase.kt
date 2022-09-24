package com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases

import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import javax.inject.Inject

class SendAddedCaseUseCase @Inject constructor(
    private val addCaseRepository: AddCaseRepository
) {
    operator fun invoke(addedCase: AddedCaseModel) {
        return addCaseRepository.sendAddedCase(addedCase)
    }
}