package com.example.csgocaseswatcherapp.features.start.view

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.csgocaseswatcherapp.features.start.domain.usecases.GetPreferredCurrencyUseCase
import com.example.csgocaseswatcherapp.features.start.domain.usecases.SendPreferredCurrencyUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StartViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getPreferredCurrencyUseCase: GetPreferredCurrencyUseCase = mockk(relaxed = true)
    private val sendPreferredCurrencyUseCase: SendPreferredCurrencyUseCase = mockk(relaxed = true)

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `initial state is Content with USD`() {

        val viewModel = StartViewModel(getPreferredCurrencyUseCase, sendPreferredCurrencyUseCase)

        assertThat(viewModel.uiState.value)
            .isEqualTo(StartViewState.Content("USD"))
    }
}