package com.example.csgocaseswatcherapp.features.addcase.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddCaseRoute(
    viewModel: AddCaseViewModel,
    navigateToPortfolio: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AddCaseEvent.NavigateToPortfolioWithAddedCase -> navigateToPortfolio()
                is AddCaseEvent.ShowValidationError -> Toast.makeText(
                    context,
                    event.error.resId,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    AddCaseScreen(
        state,
        onAction = { action -> viewModel.handleAction(action) },
    )
}