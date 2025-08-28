package com.example.csgocaseswatcherapp.features.addcasefragment.view

import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel

sealed class AddCaseViewState {

    data object Loading : AddCaseViewState()

    data class Content(
        val caseName: String,
        val amount: String,
        val price: String,
        val caseNameSearchQuery: String = "",
        val isAddCaseButtonActive: Boolean = false,
        val caseNameSuggestionList: List<String> = listOf(
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
    ) : AddCaseViewState()
}