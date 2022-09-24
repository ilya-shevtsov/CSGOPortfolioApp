package com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.data.entities.AddedCaseDto
import com.example.csgocaseswatcherapp.presentation.screens.addcasefragment.view.entities.AddedCaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddCaseViewModel : ViewModel() {

    val uiState: MutableStateFlow<AddCaseViewState> =
        MutableStateFlow(value = AddCaseViewState.Loading)

    val uiEvent = MutableSharedFlow<AddCaseViewEvent>()

    fun handleAction(action: AddCaseViewAction) {
        when (action) {
            is AddCaseViewAction.OnCaseAddedClicked -> handleOnCaseAddedClicked(action.addedCase)
        }
    }

    private fun handleOnCaseAddedClicked(addedCase: AddedCaseModel) {
        sendAddedCase(addedCase)
        viewModelScope.launch {
            uiEvent.emit(
                AddCaseViewEvent.NavigateToPortfolioWithAddedCase(
                    addedCase
                )
            )
        }
    }

    private fun sendAddedCase(addedCase: AddedCaseModel) {
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