package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.SendAddedCaseUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCaseViewModel @Inject constructor(
    private val sendAddedCaseUseCase: SendAddedCaseUseCase
) :
    ViewModel() {

    val uiState: MutableStateFlow<AddCaseViewState> =
        MutableStateFlow(value = AddCaseViewState.Loading)

    val uiEvent = MutableSharedFlow<AddCaseViewEvent>()

    fun handleAction(action: AddCaseViewAction) {
        when (action) {
            is AddCaseViewAction.OnCaseAddedClicked -> handleOnCaseAddedClicked(action.addedCase)
        }
    }

    private fun handleOnCaseAddedClicked(addedCase: AddedCaseModel) {
        sendAddedCaseUseCase.invoke(addedCase)
        viewModelScope.launch {
            uiEvent.emit(
                AddCaseViewEvent.NavigateToPortfolioWithAddedCase(
                    addedCase
                )
            )
        }
    }

    private val caseNameSuggestions = listOf(
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