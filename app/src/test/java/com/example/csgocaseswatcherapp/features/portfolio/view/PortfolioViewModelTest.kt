package com.example.csgocaseswatcherapp.features.portfolio.view

import app.cash.turbine.test
import assertk.Assert
import assertk.assertThat
import assertk.assertions.first
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import com.example.csgocaseswatcherapp.features.portfolio.domain.usecases.GetPortfolioDataUseCase
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItem
import com.example.csgocaseswatcherapp.features.portfolio.view.entities.PortfolioItemModel
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortState
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
class PortfolioViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getPortfolioDataUseCase: GetPortfolioDataUseCase = mockk()

    private lateinit var viewModel: PortfolioViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        mockGetPortfolioData(listOf(fakePortfolioData))
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

    @Test
    fun `after Loading state is Content`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Loading::class)


            viewModel.handleAction(PortfolioViewAction.OnCreate)

            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Content::class)
        }
    }

    @Test
    fun `content contains correct data`() = runTest {
        viewModel = buildViewModel()

        viewModel.handleAction(PortfolioViewAction.OnCreate)

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Loading::class)
            assertThat(awaitItem())
                .isInstanceOf(PortfolioViewState.Content::class)
                .prop(PortfolioViewState.Content::portfolioItemModelList).first()
                .isEqualTo(fakePortfolioModel)
        }
    }

    @Test
    fun `when addCase clicked navigate to add case`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiEvent.test {
            viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked)

            assertThat(awaitItem()).isEqualTo(PortfolioViewEvent.NavigateToAddCase)
        }
    }

    @Test
    fun `when sorting clicked, show sorting sheet`() = runTest {
        viewModel = buildViewModel()

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Loading::class)

            viewModel.handleAction(PortfolioViewAction.OnCreate)

            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Content::class)

            viewModel.handleAction(PortfolioViewAction.OnSortClicked)

            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Content::class)
                .prop(PortfolioViewState.Content::isSortingSheetVisible)
                .isEqualTo(true)
        }
    }

    @Test
    fun `when sorted by name, show in alphabetical order`() = runTest {
        assertSorted(
            originalOrder = originalPortfolioData,
            sortMethod = SortState.BY_NAME,
            expectedOrder = byNamePortfolioData
        )

    }

    @Test
    fun `when sorted by about, show amount in descending order`() = runTest {
        assertSorted(
            originalOrder = originalPortfolioData,
            sortMethod = SortState.BY_AMOUNT,
            expectedOrder = byAmountPortfolioData
        )
    }

    @Test
    fun `when sorted by price, show price in descending order`() = runTest {
        assertSorted(
            originalOrder = originalPortfolioData,
            sortMethod = SortState.BY_PRICE,
            expectedOrder = byPricePortfolioData
        )
    }

    @Test
    fun `when sorted by profit loss, show profit in descending order`() = runTest {
        assertSorted(
            originalOrder = originalPortfolioData,
            sortMethod = SortState.BY_PROFIT_LOSS,
            expectedOrder = byProfitLossPortfolioData
        )
    }

    private suspend fun assertSorted(
        originalOrder: List<PortfolioItem>,
        sortMethod: SortState,
        expectedOrder: List<PortfolioItem>
    ) {
        mockGetPortfolioData(originalOrder)
        viewModel = buildViewModel()

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(PortfolioViewState.Loading::class)

            viewModel.handleAction(PortfolioViewAction.OnCreate)
            awaitItem()

            viewModel.handleAction(PortfolioViewAction.OnSortingMethodSelected(sortMethod))

            assertThat(awaitItem()).hasCasesInOrder(expectedOrder)
        }
    }

    private fun Assert<PortfolioViewState>.hasCasesInOrder(expectedOrder: List<PortfolioItem>) =
        this
            .isInstanceOf(PortfolioViewState.Content::class)
            .prop(PortfolioViewState.Content::portfolioItemModelList)
            .transform { it.map(PortfolioItemModel::itemName) }
            .isEqualTo(expectedOrder.names())

    private fun List<PortfolioItem>.names(): List<String> {
        return this.map { it.name }
    }

    private fun mockGetPortfolioData(returnValue: List<PortfolioItem>) {
        coEvery { getPortfolioDataUseCase.invoke() } returns returnValue
    }

    private fun createPortfolioItem(
        image: String = "",
        name: String = "",
        amount: Int = 0,
        price: Double = 0.0,
        overallValue: Double = 0.0,
        profitLoss: Double = 0.0,
    ): PortfolioItem {
        return PortfolioItem(
            image = image,
            name = name,
            amount = amount,
            price = price,
            overallValue = overallValue,
            profitLoss = profitLoss
        )
    }

    private val originalPortfolioData = listOf(
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
    )

    private val byNamePortfolioData = listOf(
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
    )

    private val byAmountPortfolioData = listOf(
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
    )
    private val byPricePortfolioData = listOf(
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
    )
    private val byValuePortfolioData = listOf(
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
    )

    private val byProfitLossPortfolioData = listOf(
        createPortfolioItem(
            name = "B",
            amount = 3,
            price = 1.0,
            overallValue = 3.0,
            profitLoss = 2.0
        ),
        createPortfolioItem(
            name = "C",
            amount = 1,
            price = 2.0,
            overallValue = 2.0,
            profitLoss = 0.5
        ),
        createPortfolioItem(
            name = "A",
            amount = 2,
            price = 3.0,
            overallValue = 6.0,
            profitLoss = -1.0
        ),
    )

    private val fakePortfolioData = PortfolioItem(
        image = "https://api.steamapis.com/image/item/730/Danger%20Zone%20Case",
        name = "Danger Zone Case",
        amount = 2000,
        price = 1.0,
        overallValue = 2000.0,
        profitLoss = 0.0
    )

    private val fakePortfolioModel = PortfolioItemModel(
        itemImage = "https://api.steamapis.com/image/item/730/Danger%20Zone%20Case",
        itemName = "Danger Zone Case",
        totalValue = "$2000.00",
        amountPrice = "2000 cases • $1.00",
        profitLoss = "+0.00 $ (0.0 %)"
    )
}