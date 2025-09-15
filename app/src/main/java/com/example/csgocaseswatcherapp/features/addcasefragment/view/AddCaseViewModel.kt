package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseState
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.NameSuggestionResult
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.GetCaseSuggestionListUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.SendAddedCaseUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
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
            name = "",
            amountInput = "",
            amount = 0,
            priceInput = "",
            price = 0.0,
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
                        state.price > 0.0 && state.amount > 0 && state.originalNameSuggestionList.contains(
                            state.name
                        )

                    AddCaseViewState.Content(
                        name = state.name,
                        amount = state.amountInput,
                        price = state.priceInput,
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
            businessState.update { it.copy(name = action.name) }
        }
    }

    private fun handleOnNameChanged(action: AddCaseViewAction.OnNameChanged) {
        viewModelScope.launch {
            businessState.update {
                it.copy(
                    name = action.name,
                    caseNameSearchQuery = action.name
                )
            }

        }
    }

    private fun handleOnAmountChanged(action: AddCaseViewAction.OnAmountChanged) {
        val raw = action.amount

        val parsed = raw.toIntOrNull()

        businessState.update {
            it.copy(
                amountInput = raw,
                amount = parsed ?: 0
            )
        }
    }


    private fun handleOnPriceChanged(action: AddCaseViewAction.OnPriceChanged) {
        val raw = action.price

        val parsed = raw.replace(',', '.').toDoubleOrNull()

        businessState.update {
            it.copy(
                priceInput = raw,                // keep what user typed
                price = parsed ?: 0.0    // business value: only valid Double
            )
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
        val addedCase = AddedCase(
            name = businessState.value.name,
            amount = businessState.value.amount,
            purchasePrice = businessState.value.price
        )
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