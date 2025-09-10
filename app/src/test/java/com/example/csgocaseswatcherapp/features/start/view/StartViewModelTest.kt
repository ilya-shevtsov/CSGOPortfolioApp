package com.example.csgocaseswatcherapp.features.start.view

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.csgocaseswatcherapp.features.start.domain.entities.PreferredCurrency
import com.example.csgocaseswatcherapp.features.start.domain.usecases.GetPreferredCurrencyUseCase
import com.example.csgocaseswatcherapp.features.start.domain.usecases.SendPreferredCurrencyUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StartViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: StartViewModel

    private val getPreferredCurrencyUseCase: GetPreferredCurrencyUseCase = mockk()
    private val sendPreferredCurrencyUseCase: SendPreferredCurrencyUseCase = mockk(relaxed = true)



    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = StartViewModel(getPreferredCurrencyUseCase, sendPreferredCurrencyUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init maps currency code to correct label (1 returns USD)`() {
        coEvery { getPreferredCurrencyUseCase() } returns PreferredCurrency(preferredCurrency = 1)
        testDispatcher.scheduler.advanceUntilIdle()
        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("USD"))
    }

    @Test
    fun `init maps currency code to correct label (5 returns RUB)`() {
        coEvery { getPreferredCurrencyUseCase() } returns PreferredCurrency(preferredCurrency = 5)
        testDispatcher.scheduler.advanceUntilIdle()
        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("RUB"))
    }

    @Test
    fun `initial state is Content with USD`() {
        assertThat(viewModel.uiState.value)
            .isEqualTo(StartViewState.Content("USD"))
    }

    @Test
    fun `select RUB updates state and sends new preferred currency with value 5`() {
        viewModel.handleAction(StartViewAction.OnCurrencySelected("RUB"))
        assertThat(viewModel.uiState.value).isEqualTo(StartViewState.Content("RUB"))
        verify(exactly = 1) {sendPreferredCurrencyUseCase(PreferredCurrency((5)))}
    }


}