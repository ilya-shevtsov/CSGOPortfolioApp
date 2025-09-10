package com.example.csgocaseswatcherapp.features.start.view

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.usecases.GetPreferredCurrencyUseCase
import com.example.csgocaseswatcherapp.features.start.domain.usecases.SendPreferredCurrencyUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
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
class StartViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getPreferredCurrencyUseCase: GetPreferredCurrencyUseCase = mockk()
    private val sendPreferredCurrencyUseCase: SendPreferredCurrencyUseCase = mockk(relaxed = true)
    private lateinit var viewModel: StartViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildViewModel() =
        StartViewModel(getPreferredCurrencyUseCase, sendPreferredCurrencyUseCase)

    @Test
    fun `init maps currency code 1 to USD`() {
        coEvery { getPreferredCurrencyUseCase() } returns PreferredCurrency(preferredCurrency = 1)

        viewModel = buildViewModel()

        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("USD"))
    }

    @Test
    fun `init maps currency code 5 to RUB`() {
        coEvery { getPreferredCurrencyUseCase() } returns PreferredCurrency(preferredCurrency = 5)

        viewModel = buildViewModel()

        testDispatcher.scheduler.advanceUntilIdle()


        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("RUB"))
    }

    @Test
    fun `initial state is Content with USD`() {
        viewModel = buildViewModel()

        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("USD"))
    }

    @Test
    fun `select RUB updates state and sends new preferred currency with value 5`() {
        coEvery { getPreferredCurrencyUseCase() } returns PreferredCurrency(1)

        viewModel = buildViewModel()

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.handleAction(StartViewAction.OnCurrencySelected("RUB"))

        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("RUB"))

        coVerify(exactly = 1) {
            sendPreferredCurrencyUseCase(PreferredCurrency(5))
        }
    }

    @Test
    fun `when currency clicked navigate to currency change screen`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(StartViewAction.OnCurrencyChangeClicked)

            assertThat(awaitItem()).isEqualTo(StartViewEvent.NavigateToCurrencyChange)
        }
    }

    @Test
    fun `when analytics clicked navigate to analysis`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(StartViewAction.OnAnalyticsClicked)

            assertThat(awaitItem()).isEqualTo(StartViewEvent.NavigateToAnalytics)
        }
    }

    @Test
    fun `when portfolio clicked navigate to portfolio`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(StartViewAction.OnPortfolioClicked)

            assertThat(awaitItem()).isEqualTo(StartViewEvent.NavigateToPortfolio)
        }
    }

    @Test
    fun `when overview clicked navigate to case overview`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(StartViewAction.OnCaseOverviewClicked)

            assertThat(awaitItem()).isEqualTo(StartViewEvent.NavigateToCaseOverview)
        }
    }

    @Test
    fun `init error path - sets Error when use case throws`() {
        coEvery { getPreferredCurrencyUseCase() } throws RuntimeException("error")

        viewModel = buildViewModel()
        testDispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Error)
    }
}