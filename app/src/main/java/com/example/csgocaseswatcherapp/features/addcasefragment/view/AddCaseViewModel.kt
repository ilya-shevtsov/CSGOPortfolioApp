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
            amount = null,
            priceInput = "",
            price = null,
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
                                it.contains(state.caseNameSearchQuery, ignoreCase = true)
                            }
                        }

                    val nameError = state.name.validateName(state.originalNameSuggestionList)
                    val amountError = state.amountInput.validateAmount()
                    val priceError = state.priceInput.validatePrice()

                    val addCaseButtonIsActive =
                        nameError == null && amountError == null && priceError == null

                    AddCaseViewState.Content(
                        name = state.name,
                        amount = state.amountInput,
                        price = state.priceInput,
                        caseNameSearchQuery = state.caseNameSearchQuery,
                        isAddCaseButtonActive = addCaseButtonIsActive,
                        caseNameSuggestionList = filtered,
                        nameError = nameError,
                        amountError = amountError,
                        priceError = priceError
                    )
                }
            }
            uiState.value = ui
        }.launchIn(viewModelScope)
    }


    // Как тут лучше избавиться от hardcode string-ов?
    private fun String.validateName(allowedNames: List<String>): String? =
        when {
            this.isBlank() -> "Choose a case"
            !allowedNames.contains(this) -> "Unknown case"
            else -> null
        }

    private fun String.validateAmount(): String? {
        val amountInt = this.toIntOrNull()
        return when {
            this.isBlank() -> "Enter amount"
            amountInt == null -> "Amount must be an integer"
            amountInt <= 0 -> "Amount must be > 0"
            else -> null
        }
    }

    private fun String.validatePrice(): String? {
        val danglingDecimal = endsWith('.') || endsWith(',')
        val priceDouble = this.toDoubleOrNull()
        return when {
            isBlank() -> "Enter price"
            danglingDecimal -> "Finish the number (e.g., 0.0)"
            priceDouble == null -> "Price must be a number"
            priceDouble <= 0.0 -> "Price must be > 0"
            else -> null
        }
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
        businessState.update { state ->
            state.copy(
                amountInput = raw,
                amount = raw.toIntOrNull()
            )
        }
    }


    private fun handleOnPriceChanged(action: AddCaseViewAction.OnPriceChanged) {
        val raw = action.price

        businessState.update { state ->
            state.copy(
                priceInput = raw,
                price = raw.toDoubleOrNull()
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
        val currentState = businessState.value

        val amount = currentState.amount
        val price = currentState.price

        if (amount == null || price == null) {
            viewModelScope.launch {
                uiEvent.emit(AddCaseViewEvent.ShowValidationError("Please fill amount and price correctly"))
            }
            return
        }

        val addedCase = AddedCase(
            name = currentState.name,
            amount = amount,
            purchasePrice = price
        )

        sendAddedCaseUseCase.invoke(addedCase)

        viewModelScope.launch {
            uiEvent.emit(
                AddCaseViewEvent.NavigateToPortfolioWithAddedCase
            )
        }
    }
}