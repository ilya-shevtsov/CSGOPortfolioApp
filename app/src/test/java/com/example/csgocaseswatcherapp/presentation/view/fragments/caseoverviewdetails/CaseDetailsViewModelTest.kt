package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverviewdetails

import app.cash.turbine.test
import com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem.CaseOverviewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CaseDetailsViewModelTest {

    private val viewModel = CaseDetailsViewModel()

    @Test
    fun `should show loading when uiState firstly created`() = runBlocking {

        viewModel.uiState.test {
            assertEquals(CaseDetailsViewState.Loading, awaitItem())

        }
    }

    @Test
    fun `should show case details when case data received`() = runBlocking {
        val caseModel = CaseOverviewModel(
            caseName = "Huntsman Weapon Case",
            releaseDate = "01.05.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 00.00,
            volume = 0,
            medianPrice = 00.00,
            imageUrl = "https://api.steamapis.com/image/item/730/Huntsman%20Weapon%20Case",
            description =
            "The Huntsman Weapon Case"
        )
        val viewActionItemProvidedAction = CaseDetailsViewAction.OnItemProvided(caseModel)
        viewModel.handleAction(viewActionItemProvidedAction)
        viewModel.uiState.test {
            assertEquals(CaseDetailsViewState.Content(caseModel), awaitItem())

        }
    }
}