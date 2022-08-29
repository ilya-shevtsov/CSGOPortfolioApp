package com.example.csgocaseswatcherapp.presentation.view.fragments.addCaseFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel
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
        viewModelScope.launch {
            uiEvent.emit(
                AddCaseViewEvent.NavigateToPortfolioWithAddedCase(
                    addedCase
                )
            )
        }
    }
}