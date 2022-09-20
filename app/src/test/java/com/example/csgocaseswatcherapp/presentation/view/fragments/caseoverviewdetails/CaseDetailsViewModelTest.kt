package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import app.cash.turbine.test
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CaseDetailsViewModelTest {

    private val viewModel = CaseDetailsViewModel()

    @Test
    fun `should show loading when uiState firstly created`() = runBlocking {

        viewModel.uiState.test {
            assertEquals(CaseDetailsViewState.Loading,awaitItem())

        }
    }

    @Test
    fun `should show case details when case data received`() = runBlocking {

        viewModel.uiState.test {
            assertEquals(CaseDetailsViewState.Loading,awaitItem())

        }
    }
}