package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseFieldData
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AddCaseState
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.AmountValidationResult
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.NameSuggestionResult
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.PriceValidationResult
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.GetCaseSuggestionListUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.domain.usecases.SendAddedCaseUseCase
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
            caseNameSearchQuery = "",
            nameSuggestionResult = NameSuggestionResult.Loading,
            originalNameSuggestionList = emptyList(),
            amountField = AddCaseFieldData(input = "", result = AmountValidationResult.Fail(error = AddCaseError.AMOUNT_EMPTY)),
            priceField  = AddCaseFieldData(input = "", result = PriceValidationResult.Fail(error = AddCaseError.PRICE_EMPTY))
        )
    }

    val uiState: MutableStateFlow<AddCaseViewState> =
        MutableStateFlow(value = initState())

    val uiEvent = MutableSharedFlow<AddCaseEvent>()

    private val businessState = MutableStateFlow(
        initBusinessState()
    )

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
                                it.name.contains(state.caseNameSearchQuery, ignoreCase = true)
                            }
                        }
                    val nameErr =
                        state.name.validateName(state.originalNameSuggestionList.map { addCaseSuggestion -> addCaseSuggestion.name })
                    val amountErr = state.amountField.result.toErrorResOrNull()
                    val priceErr  = state.priceField.result.toErrorResOrNull()

                    val nameErrRes = nameErr?.resId

                    val addCaseButtonIsActive = nameErrRes == null && amountErr == null && priceErr == null

                    AddCaseViewState.Content(
                        name = state.name,
                        amount = state.amountField.input,
                        price = state.priceField.input,
                        caseNameSearchQuery = state.caseNameSearchQuery,
                        isAddCaseButtonActive = addCaseButtonIsActive,
                        caseNameSuggestionList = filtered,
                        nameError = nameErrRes,
                        amountError = amountErr,
                        priceError = priceErr
                    )
                }
            }
            uiState.value = ui
        }.launchIn(viewModelScope)
    }

    private fun String.validateName(allowedNames: List<String>): AddCaseError? =
        when {
            this.isBlank() -> AddCaseError.NAME_EMPTY
            !allowedNames.contains(this) -> AddCaseError.NAME_UNKNOWN
            else -> null
        }

    private fun parseAmount(raw: String): AmountValidationResult =
        when {
            raw.isBlank() -> AmountValidationResult.Fail(AddCaseError.AMOUNT_EMPTY)
            raw.toIntOrNull() == null -> AmountValidationResult.Fail(AddCaseError.AMOUNT_NOT_INT)
            raw.toInt() <= 0 -> AmountValidationResult.Fail(AddCaseError.AMOUNT_NOT_POSITIVE)
            else -> AmountValidationResult.Success(raw.toInt())
        }

    private fun parsePrice(raw: String): PriceValidationResult {
        val dangling = raw.endsWith('.') || raw.endsWith(',')
        val normalized = raw.replace(',', '.')
        val price = normalized.toDoubleOrNull()

        return when {
            raw.isBlank() -> PriceValidationResult.Fail(AddCaseError.PRICE_EMPTY)
            dangling -> PriceValidationResult.Fail(AddCaseError.PRICE_DANGLING_DECIMAL)
            price == null -> PriceValidationResult.Fail(AddCaseError.PRICE_NOT_NUMBER)
            price <= 0.0 -> PriceValidationResult.Fail(AddCaseError.PRICE_NOT_POSITIVE)
            else -> PriceValidationResult.Success(price)
        }
    }

    @StringRes
    private fun AmountValidationResult.toErrorResOrNull(): Int? = when (this) {
        is AmountValidationResult.Success -> null
        is AmountValidationResult.Fail -> this.error.resId
    }

    @StringRes
    private fun PriceValidationResult.toErrorResOrNull(): Int? = when (this) {
        is PriceValidationResult.Success -> null
        is PriceValidationResult.Fail -> this.error.resId
    }

    private fun handleOnSuggestionClicked(action: AddCaseAction.OnSuggestionClicked) {
        viewModelScope.launch {
            businessState.update { it.copy(name = action.name) }
        }
    }

    private fun handleOnNameChanged(action: AddCaseAction.OnNameChanged) {
        viewModelScope.launch {
            businessState.update {
                it.copy(
                    name = action.name,
                    caseNameSearchQuery = action.name
                )
            }
        }
    }

    private fun handleOnAmountChanged(action: AddCaseAction.OnAmountChanged) {
        val raw = action.amount
        businessState.update { state ->
            state.copy(amountField = AddCaseFieldData(input = raw, result = parseAmount(raw)))
        }
    }

    private fun handleOnPriceChanged(action: AddCaseAction.OnPriceChanged) {
        val raw = action.price
        businessState.update { state ->
            state.copy(priceField = AddCaseFieldData(input = raw, result = parsePrice(raw)))
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
        val current = businessState.value

        val amount = (current.amountField.result as? AmountValidationResult.Success)?.amount
        val price  = (current.priceField.result  as? PriceValidationResult.Success)?.price

        if (amount == null || price == null) {
            viewModelScope.launch {
                uiEvent.emit(AddCaseEvent.ShowValidationError("Please fill amount and price correctly"))
            }
            return
        }

        val addedCase = AddedCase(
            name = current.name,
            amount = amount,
            purchasePrice = price
        )

        sendAddedCaseUseCase.invoke(addedCase)

        viewModelScope.launch {
            uiEvent.emit(AddCaseEvent.NavigateToPortfolioWithAddedCase)
        }
    }
}