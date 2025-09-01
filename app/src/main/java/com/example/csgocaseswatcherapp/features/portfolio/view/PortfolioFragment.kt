package com.example.csgocaseswatcherapp.features.portfolio.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingBottomModal
import com.example.csgocaseswatcherapp.features.sortingmodal.view.SortingModalViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sortingViewModel: SortingModalViewModel by viewModels { viewModelFactory }

    private val viewModel: PortfolioViewModel by viewModels { viewModelFactory }

    private lateinit var composeView: ComposeView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        composeView.setContent {
            AppTheme{
                PortfolioIntegration(
                    viewModel,
                    sortingViewModel
                )
            }
        }
    }

    private fun handleNavigateToAddCase() {
        findNavController().navigate(R.id.addCaseFragment)
    }

    private fun handleNavigateToPortfolioDetails(uiEvent: PortfolioViewEvent.NavigateToPortfolioDetails) {
        val action = PortfolioFragmentDirections.actionPortfolioFragmentToPortfolioDetailsFragment(
            uiEvent.portfolioItemListArgs
        )
        findNavController().navigate(action)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PortfolioIntegration(
        viewModel: PortfolioViewModel,
        sortingViewModel: SortingModalViewModel
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val showSortingSheet = remember { mutableStateOf(false) }
        val scrollSignal = remember { androidx.compose.runtime.mutableIntStateOf(0) }


        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is PortfolioViewEvent.NavigateToAddCase -> handleNavigateToAddCase()
                    is PortfolioViewEvent.NavigateToPortfolioDetails -> handleNavigateToPortfolioDetails(event)
                    is PortfolioViewEvent.NavigateToSorting -> {
                        showSortingSheet.value = true
                    }
                }
            }
        }

        PortfolioScreen(
            state = state,
            onDetailsClicked = { viewModel.handleAction(PortfolioViewAction.OnPortfolioDetailsClicked) },
            onAddCaseClicked = { viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked) },
            onSortingClicked = { viewModel.handleAction(PortfolioViewAction.OnSortClicked) },
            scrollSignal = scrollSignal.intValue
        )

        if (showSortingSheet.value) {
            ModalBottomSheet(
                onDismissRequest = { showSortingSheet.value = false },
                sheetState = rememberModalBottomSheetState()
            ) {
                SortingBottomModal(
                    viewModel = sortingViewModel,
                    onDismissRequest = { showSortingSheet.value = false },
                    onSortingSelected = { sortingMethod ->
                        viewModel.handleAction(
                            PortfolioViewAction.OnSortingMethodSelected(sortingMethod)
                        )
                        scrollSignal.intValue++
                        showSortingSheet.value = false
                    }
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}