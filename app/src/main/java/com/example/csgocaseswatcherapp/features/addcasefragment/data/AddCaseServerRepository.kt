package com.example.csgocaseswatcherapp.features.addcasefragment.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.addcasefragment.data.entities.AddedCaseDto
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCaseServerRepository @Inject constructor() : AddCaseRepository {

    override fun sendAddedCase(addedCase: AddedCaseModel) {
        val addedCaseDto = AddedCaseDto(
            name = addedCase.name,
            amount = addedCase.amount,
            purchasePrice = addedCase.purchasePrice
        )
        CoroutineScope(Dispatchers.IO).launch {
            ApiTools.getApiService().postAddedCase(addedCaseDto)
        }
    }
}