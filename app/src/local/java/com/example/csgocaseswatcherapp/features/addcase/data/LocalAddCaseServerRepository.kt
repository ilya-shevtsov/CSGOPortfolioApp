package com.example.csgocaseswatcherapp.features.addcase.data

import com.LocalMockStore
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseRepository
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddedCase
import javax.inject.Inject

class LocalAddCaseServerRepository @Inject constructor(
    private val store: LocalMockStore
) : AddCaseRepository {

    override fun sendAddedCase(addedCase: AddedCase) {
        val img = suggestions.firstOrNull { it.name == addedCase.name }?.imageUrl ?: ""
        store.addCaseToPortfolio(addedCase, img)
    }

    override fun getSuggestionList(): List<AddCaseSuggestion> {
        return suggestions
    }

    private val suggestions = listOf(
        AddCaseSuggestion(
            name = "Chroma Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
        ),
        AddCaseSuggestion(
            name = "Chroma 2 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%202%20Case"
        ),
        AddCaseSuggestion(
            name = "Chroma 3 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%203%20Case"
        ),
        AddCaseSuggestion(
            name = "Clutch Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Clutch%20Case"
        ),
        AddCaseSuggestion(
            name = "CS:GO Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "CS:GO Weapon Case 2",
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%202"
        ),
        AddCaseSuggestion(
            name = "CS:GO Weapon Case 3",
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%203"
        ),
        AddCaseSuggestion(
            name = "CS20 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/CS20%20Case"
        ),
        AddCaseSuggestion(
            name = "Danger Zone Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Danger%20Zone%20Case"
        ),
        AddCaseSuggestion(
            name = "eSports 2013 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Case"
        ),
        AddCaseSuggestion(
            name = "eSports 2013 Winter Case",
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Winter%20Case"
        ),
        AddCaseSuggestion(
            name = "eSports 2014 Summer Case",
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202014%20Summer%20Case"
        ),
        AddCaseSuggestion(
            name = "Falchion Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Falchion%20Case"
        ),
        AddCaseSuggestion(
            name = "Fracture Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Fracture%20Case"
        ),
        AddCaseSuggestion(
            name = "Gamma Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Gamma%20Case"
        ),
        AddCaseSuggestion(
            name = "Gamma 2 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Gamma%202%20Case"
        ),
        AddCaseSuggestion(
            name = "Glove Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Glove%20Case"
        ),
        AddCaseSuggestion(
            name = "Horizon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Horizon%20Case"
        ),
        AddCaseSuggestion(
            name = "Huntsman Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Huntsman%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Bravo Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Bravo%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Breakout Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Broken Fang Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Broken%20Fang%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Hydra Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Hydra%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Phoenix Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Phoenix%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Vanguard Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Vanguard%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "Operation Wildfire Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Wildfire%20Case"
        ),
        AddCaseSuggestion(
            name = "Prisma Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Prisma%20Case"
        ),
        AddCaseSuggestion(
            name = "Prisma 2 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Prisma%202%20Case"
        ),
        AddCaseSuggestion(
            name = "Revolver Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Revolver%20Case"
        ),
        AddCaseSuggestion(
            name = "Shadow Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Shadow%20Case"
        ),
        AddCaseSuggestion(
            name = "Shattered Web Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Shattered%20Web%20Case"
        ),
        AddCaseSuggestion(
            name = "Snakebite Case",
            imageUrl = "https://www.csgodatabase.com/images/containers/webp/Snakebite_Case.webp"
        ),
        AddCaseSuggestion(
            name = "Spectrum Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Spectrum%20Case"
        ),
        AddCaseSuggestion(
            name = "Spectrum 2 Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Spectrum%202%20Case"
        ),
        AddCaseSuggestion(
            name = "Winter Offensive Weapon Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Winter%20Offensive%20Weapon%20Case"
        ),
        AddCaseSuggestion(
            name = "Dreams & Nightmares Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Dreams%20%26%20Nightmares%20Case"
        ),
        AddCaseSuggestion(
            name = "Recoil Case",
            imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case"
        )
    )
}