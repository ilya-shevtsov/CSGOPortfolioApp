package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

import androidx.lifecycle.ViewModel
import com.example.csgocaseswatcherapp.presentation.view.fragments.start.StartViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class CurrencyChangeViewModel : ViewModel(){

    val uiState = MutableStateFlow(value = createInitialState())

    val uiEvent = MutableSharedFlow<CurrencyChangeViewEvent>()


    private fun createInitialState(): CurrencyChangeViewState = CurrencyChangeViewState(listOf("USD","RUB"))

}