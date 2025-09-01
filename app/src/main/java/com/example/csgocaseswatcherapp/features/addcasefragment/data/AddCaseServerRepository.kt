package com.example.csgocaseswatcherapp.features.addcasefragment.data

import com.example.csgocaseswatcherapp.api.ApiTools
import com.example.csgocaseswatcherapp.features.addcasefragment.data.entities.AddedCaseDto
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCaseServerRepository @Inject constructor() : AddCaseRepository {

    override fun sendAddedCase(addedCase: AddedCase) {
        val addedCaseDto = AddedCaseDto(
            name = addedCase.name,
            amount = addedCase.amount,
            purchasePrice = addedCase.purchasePrice
        )
        CoroutineScope(Dispatchers.IO).launch {
            ApiTools.getApiService().postAddedCase(addedCaseDto)
        }
    }

    override fun getSuggestionList(): List<String> {
        return listOf(
            "Chroma Case", "Chroma 2 Case", "Chroma 3 Case", "Clutch Case",
            "CSGO Weapon Case", "CSGO Weapon Case 2", "CSGO Weapon Case 3",
            "CS20 Case", "Danger Zone Case", "eSports 2013 Case",
            "eSports 2013 Winter Case", "eSports 2014 Summer Case",
            "Falchion Case", "Fracture Case", "Gamma Case", "Gamma 2 Case",
            "Glove Case", "Horizon Case", "Huntsman Weapon Case",
            "Operation Bravo Case", "Operation Breakout Weapon Case",
            "Operation Broken Fang Case", "Operation Hydra Case",
            "Operation Phoenix Weapon Case", "Operation Vanguard Weapon Case",
            "Operation Wildfire Case", "Prisma Case", "Prisma 2 Case",
            "Revolver Case", "Shadow Case", "Shattered Web Case",
            "Spectrum Case", "Spectrum 2 Case", "Winter Offensive Weapon Case",
            "Snakebite Case", "Dreams & Nightmares Case", "Recoil Case"
        )
    }
}