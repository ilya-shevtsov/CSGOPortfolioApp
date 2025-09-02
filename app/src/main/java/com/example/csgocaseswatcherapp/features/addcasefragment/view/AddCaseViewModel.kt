package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseState
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.NameSuggestionResult
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.GetCaseSuggestionListUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.SendAddedCaseUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.toModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCaseViewModel @Inject constructor(
    private val sendAddedCaseUseCase: SendAddedCaseUseCase,
    private val getCaseSuggestionListUseCase: GetCaseSuggestionListUseCase
) : ViewModel() {

    private fun initState(): AddCaseViewState {
        return AddCaseViewState.Loading
    }

    private fun initBusinessState(): AddCaseState {
        return AddCaseState(
            addedCaseData = AddedCase(name = "", amount = 0, purchasePrice = 0.0),
            caseNameSearchQuery = "",
            nameSuggestionResult = NameSuggestionResult.Loading,
            originalNameSuggestionList = listOf()
        )
    }

    val uiState: MutableStateFlow<AddCaseViewState> =
        MutableStateFlow(value = initState())

    val uiEvent = MutableSharedFlow<AddCaseViewEvent>()

    private val businessState = MutableStateFlow(
        initBusinessState()
    )

    init {
        createViewStateChain()
    }

    fun handleAction(action: AddCaseViewAction) {
        when (action) {
            is AddCaseViewAction.OnCreate -> onCreate()

            is AddCaseViewAction.OnNameChanged -> handleOnNameChanged(action)

            is AddCaseViewAction.OnAmountChanged -> handleOnAmountChanged(action)

            is AddCaseViewAction.OnPriceChanged -> handleOnPriceChanged(action)

            is AddCaseViewAction.OnAddCaseClicked -> handleAddCaseClicked()

            is AddCaseViewAction.OnSuggestionClicked -> handleOnSuggestionClicked(action)
        }
    }

    private fun createViewStateChain() {
        businessState.onEach { state ->
            val ui = when (val result = state.nameSuggestionResult) {
                is NameSuggestionResult.Error -> AddCaseViewState.Error
                is NameSuggestionResult.Loading -> AddCaseViewState.Loading
                is NameSuggestionResult.Success -> {
                    val filtered =
                        if (state.caseNameSearchQuery.isBlank()) {
                            emptyList()
                        } else {
                            result.suggestionList.filter {
                                it.contains(
                                    state.caseNameSearchQuery,
                                    ignoreCase = true
                                )
                            }
                        }

                    val addCaseButtonIsActive =
                        state.addedCaseData.purchasePrice > 0.0 && state.addedCaseData.amount > 0 && state.originalNameSuggestionList.contains(
                            state.addedCaseData.name
                        )

                    AddCaseViewState.Content(
                        caseModel = state.addedCaseData.toModel(),
                        caseNameSearchQuery = state.caseNameSearchQuery,
                        isAddCaseButtonActive = addCaseButtonIsActive,
                        caseNameSuggestionList = filtered
                    )
                }
            }
            uiState.value = ui
        }.launchIn(viewModelScope)
    }

    private fun handleOnSuggestionClicked(action: AddCaseViewAction.OnSuggestionClicked) {
        viewModelScope.launch {
            businessState.update { it.copy(addedCaseData = it.addedCaseData.copy(name = action.name)) }
        }
    }

    private fun handleOnNameChanged(action: AddCaseViewAction.OnNameChanged) {
        viewModelScope.launch {
            businessState.update {
                it.copy(
                    addedCaseData = it.addedCaseData.copy(name = action.name),
                    caseNameSearchQuery = action.name
                )
            }

        }
    }

    private fun handleOnAmountChanged(action: AddCaseViewAction.OnAmountChanged) {
        viewModelScope.launch {
            businessState.update {
                val amountInt = action.amount.toIntOrNull() ?: 0
                val updated = it.copy(
                    addedCaseData = it.addedCaseData.copy(amount = amountInt)
                )
                updated
            }
        }
    }

    private fun handleOnPriceChanged(action: AddCaseViewAction.OnPriceChanged) {
        viewModelScope.launch {
            businessState.update {
                val priceD = action.price.replace(',', '.').toDoubleOrNull() ?: 0.0
                val updated = it.copy(
                    addedCaseData = it.addedCaseData.copy(purchasePrice = priceD)
                )
                updated
            }
        }
    }

    private fun onCreate() {
        viewModelScope.launch {
            loadCaseNameSuggestionList()
        }
    }

    private fun loadCaseNameSuggestionList() {
        try {
            val nameSuggestionList = getCaseSuggestionListUseCase.invoke()
            businessState.update { state ->
                state.copy(
                    nameSuggestionResult = NameSuggestionResult.Success(nameSuggestionList),
                    originalNameSuggestionList = nameSuggestionList
                )
            }

        } catch (throwable: Throwable) {
            businessState.update { state ->
                state.copy(
                    nameSuggestionResult = NameSuggestionResult.Error(
                        throwable.message
                    )
                )
            }
        }
    }

    private fun handleAddCaseClicked() {
        val addedCase = businessState.value.addedCaseData
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