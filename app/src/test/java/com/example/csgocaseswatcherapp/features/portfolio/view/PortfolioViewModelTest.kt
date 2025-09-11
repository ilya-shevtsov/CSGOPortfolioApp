package com.example.csgocaseswatcherapp.features.portfolio.view

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getPortfolioDataUseCase: GetPortfolioDataUseCase = mockk()

    private lateinit var viewModel: PortfolioViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildViewModel() = PortfolioViewModel(getPortfolioDataUseCase)


    @Test
    fun `init state is Loading`() {

        viewModel = buildViewModel()

        assertThat(viewModel.uiState.value).isEqualTo(PortfolioViewState.Loading)
    }


    private fun portfolioItem(
        caseName: String = "Revolution Case",
        caseImage: String = "https://example.com/rev.png",
        caseAmount: Int = 3,
        casePrice: Double = 2.15,
        caseOverallValue: Double = 6.55,
        caseProfitLoss: Double = 1.23
    ) = PortfolioItem(
        caseImage = caseImage,
        caseName = caseName,
        caseAmount = caseAmount,
        casePrice = casePrice,
        caseOverallValue = caseOverallValue,
        caseProfitLoss = caseProfitLoss
    )
}