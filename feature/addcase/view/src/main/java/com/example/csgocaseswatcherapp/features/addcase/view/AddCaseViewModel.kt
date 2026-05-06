package com.example.csgocaseswatcherapp.features.addcase.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseError
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseFieldData
import com.example.csgocaseswatcherapp.features.addcase.domain.AddCaseState
import com.example.csgocaseswatcherapp.features.addcase.domain.AmountValidationResult
import com.example.csgocaseswatcherapp.features.addcase.domain.NameSuggestionResult
import com.example.csgocaseswatcherapp.features.addcase.domain.PriceValidationResult
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddedCase
import com.example.csgocaseswatcherapp.features.addcase.domain.usecases.GetCaseSuggestionListUseCase
import com.example.csgocaseswatcherapp.features.addcase.domain.usecases.SendAddedCaseUseCase
import com.example.csgocaseswatcherapp.features.addcase.domain.usecases.ValidateCaseAmountUseCase
import com.example.csgocaseswatcherapp.features.addcase.domain.usecases.ValidateCasePriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class AddCaseViewModel @Inject constructor(
    private val sendAddedCaseUseCase: SendAddedCaseUseCase,
    private val getCaseSuggestionListUseCase: GetCaseSuggestionListUseCase,
    private val validateCaseAmountUseCase: ValidateCaseAmountUseCase,
    private val validateCasePriceUseCase: ValidateCasePriceUseCase,
    private val addCaseViewStateMapper: AddCaseViewStateMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddCaseViewState>(AddCaseViewState.Loading)
    val uiState: StateFlow<AddCaseViewState> = _uiState.asStateFlow()

    val uiEvent = MutableSharedFlow<AddCaseEvent>()

    private val addCaseState = MutableStateFlow(AddCaseState.initial())

    init {
        createViewStateChain()
    }

    fun handleAction(action: AddCaseAction) {
        when (action) {
            is AddCaseAction.OnCreate -> onCreate()
            is AddCaseAction.OnNameChanged -> handleOnNameChanged(action)
            is AddCaseAction.OnAmountChanged -> handleOnAmountChanged(action)
            is AddCaseAction.OnPriceChanged -> handleOnPriceChanged(action)
            is AddCaseAction.OnAddCaseClicked -> handleAddCaseClicked()
            is AddCaseAction.OnSuggestionClicked -> handleOnSuggestionClicked(action)
        }
    }

    private fun createViewStateChain() {
        addCaseState
            .onEach { state ->
                _uiState.value = addCaseViewStateMapper.map(state)
            }
            .launchIn(viewModelScope)
    }


    private fun onCreate() {
        viewModelScope.launch {
            loadCaseNameSuggestionList()
        }
    }


    private fun handleOnSuggestionClicked(action: AddCaseAction.OnSuggestionClicked) {
        addCaseState.update { state ->
            state.copy(
                name = action.name,
                caseNameSearchQuery = action.name
            )
        }
    }

    private fun handleOnNameChanged(action: AddCaseAction.OnNameChanged) {
        viewModelScope.launch {
            addCaseState.update { state ->
                state.copy(
                    name = action.name,
                    caseNameSearchQuery = action.name
                )
            }
        }
    }

    private fun handleOnAmountChanged(action: AddCaseAction.OnAmountChanged) {
        addCaseState.update { state ->
            state.copy(
                amountField = AddCaseFieldData(
                    input = action.amount,
                    result = validateCaseAmountUseCase(action.amount),
                    isTouched = true
                )
            )
        }
    }

    private fun handleOnPriceChanged(action: AddCaseAction.OnPriceChanged) {
        addCaseState.update { state ->
            state.copy(
                priceField = AddCaseFieldData(
                    input = action.price,
                    result = validateCasePriceUseCase(action.price),
                    isTouched = true
                )
            )
        }
    }

    private fun loadCaseNameSuggestionList() {
        runCatching {
            getCaseSuggestionListUseCase()
        }.onSuccess { nameSuggestionList ->
            addCaseState.update { state ->
                state.copy(
                    nameSuggestionResult = NameSuggestionResult.Success(nameSuggestionList),
                    originalNameSuggestionList = nameSuggestionList
                )
            }
        }.onFailure { throwable ->
            if (throwable is CancellationException) throw throwable

            addCaseState.update { state ->
                state.copy(
                    nameSuggestionResult = NameSuggestionResult.Error(throwable.message)
                )
            }
        }
    }


    private fun handleAddCaseClicked() {

        val addedCase = addCaseState.value.toAdDCaseOrNull()

        if (addedCase == null) {
            viewModelScope.launch {
                uiEvent.emit(AddCaseEvent.ShowValidationError(AddCaseError.GENERAL_VALIDATION_ERROR))
            }
            return
        }

        sendAddedCaseUseCase.invoke(addedCase)

        viewModelScope.launch {
            uiEvent.emit(AddCaseEvent.NavigateToPortfolioWithAddedCase)
        }
    }

    private fun AddCaseState.toAdDCaseOrNull(): AddedCase? {
        val amount = (amountField.result as? AmountValidationResult.Success)?.amount
        val price = (priceField.result as? PriceValidationResult.Success)?.price

        if (amount == null || price == null) return null

        return AddedCase(
            name = name,
            amount = amount,
            purchasePrice = price
        )
    }
}