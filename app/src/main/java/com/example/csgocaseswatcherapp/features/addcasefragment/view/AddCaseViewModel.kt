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
    private val sendAddedCaseUseCase: SendAddedCaseUseCase,
    getCaseSuggestionListUseCase: List<String>
) : ViewModel() {

    val uiState: MutableStateFlow<AddCaseViewState> =
        MutableStateFlow(value = AddCaseViewState.Loading)

    val uiEvent = MutableSharedFlow<AddCaseViewEvent>()

    init {
        uiState.value = AddCaseViewState.Content(
            caseName = "",
            amount = "",
            price = "",
            caseNameSearchQuery = "",
            isAddCaseButtonActive = false,
            caseNameSuggestionList = getCaseSuggestionListUseCase

        )
    }

    fun handleAction(action: AddCaseViewAction) {
        when (action) {
            is AddCaseViewAction.OnCaseAddedClicked -> {

            }

            is AddCaseViewAction.OnNameChanged -> {

            }

            AddCaseViewAction.onAddCaseClicked -> {

            }

            is AddCaseViewAction.onAmountChanged -> {

            }

            is AddCaseViewAction.onPriceChanged -> {

            }

            is AddCaseViewAction.onSuggestionClicked -> {

            }
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
}