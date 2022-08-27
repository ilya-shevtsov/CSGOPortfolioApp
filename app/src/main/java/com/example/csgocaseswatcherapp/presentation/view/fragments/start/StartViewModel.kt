package com.example.csgocaseswatcherapp.presentation.view.fragments.start

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(StartViewState("Choose Currency"))
    val uiState: StateFlow<StartViewState> = _uiState.asStateFlow()
}