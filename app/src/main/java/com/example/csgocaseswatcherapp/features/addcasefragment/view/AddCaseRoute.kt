package com.example.csgocaseswatcherapp.features.addcasefragment.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.csgocaseswatcherapp.core.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddCaseRoute(
    viewModel: AddCaseViewModel,
    navigateToPortfolio: (Screen) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AddCaseViewEvent.NavigateToPortfolioWithAddedCase -> {
                    navigateToPortfolio(Screen.Portfolio)
                }

                is AddCaseViewEvent.ShowValidationError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    AddCaseScreen(
        state,
        onAction = { action -> viewModel.handleAction(action) },
    )
}