package com.example.csgocaseswatcherapp.features.caseoverview.view

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview
import com.example.csgocaseswatcherapp.features.caseoverview.domain.usecases.GetCaseOverviewListUseCase
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModelMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CaseOverviewViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getCaseListUseCase: GetCaseOverviewListUseCase = mockk()

    private lateinit var viewModel: CaseOverviewViewModel


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
    }


    @After
        fun tearDown(){
        Dispatchers.resetMain()
    }

    private fun buildViewModel() = CaseOverviewViewModel(getCaseListUseCase)




    @Test
    fun `init state is Loading`(){
        coEvery { getCaseListUseCase() } returns emptyList()

        viewModel = buildViewModel()

        assertThat(viewModel.uiState.value).isEqualTo(CaseOverviewViewState.Loading)
    }

    @Test
    fun `successful init maps caseOverviewList to Content`(){
        val caseDomainList = listOf(domainCase())
        val caseModelList = listOf(caseModel())
        coEvery { getCaseListUseCase() } returns caseDomainList

        viewModel = buildViewModel()

        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(
            CaseOverviewViewState.Content(caseModelList)
        )
    }

    @Test
    fun `error while getting caseList results in Error`(){
        coEvery { getCaseListUseCase() } throws RuntimeException("error")

        viewModel = buildViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(CaseOverviewViewState.Error)
    }

    @Test
    fun `when case clicked navigate to details with case data`() = runTest {
        val case = caseModel()

        coEvery { getCaseListUseCase() } returns emptyList()

        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(CaseOverviewViewAction.OnCaseClicked(case))

            assertThat(awaitItem()).isEqualTo(
                CaseOverviewViewEvent.NavigateToCaseDetails(case)
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun domainCase(
        name: String = "Revolution Case",
        lowestPrice: Double = 2.15,
        volume: Int = 1245,
        medianPrice: Double = 2.30,
        imageUrl: String = "https://example.com/revolution.png",
        releaseDate: String = "2023-02-09",
        dropStatus: String = "Active",
        description: String = "Contains the AWP | Duality and other community-designed skins"
    ) = CaseOverview(
        name = name,
        lowestPrice = lowestPrice,
        volume = volume,
        medianPrice = medianPrice,
        imageUrl = imageUrl,
        releaseDate = releaseDate,
        dropStatus = dropStatus,
        description = description
    )

    private fun caseModel(
        name: String = "Revolution Case",
        lowestPrice: Double = 2.15,
        volume: Int = 1245,
        medianPrice: Double = 2.30,
        imageUrl: String = "https://example.com/revolution.png",
        releaseDate: String = "2023-02-09",
        dropStatus: String = "Active",
        description: String = "Contains the AWP | Duality and other community-designed skins"
    ) = CaseOverviewModel(
        caseName = name,
        lowestPrice = lowestPrice,
        volume = volume,
        medianPrice = medianPrice,
        imageUrl = imageUrl,
        releaseDate = releaseDate,
        dropStatus = dropStatus,
        description = description
    )
}