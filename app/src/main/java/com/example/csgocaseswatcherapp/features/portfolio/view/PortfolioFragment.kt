package com.example.csgocaseswatcherapp.features.portfolio.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
            androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        composeView.setContent {
            AppTheme(dynamicColor = false) {
                PortfolioIntegration(
                    viewModel
                )
            }
        }
    }

    private fun handleNavigateToAddCase() {
        findNavController().navigate(R.id.addCaseFragment)
    }

    private fun handleNavigateToSorting() {
        findNavController().navigate(R.id.sortingBottomSheetFragment)
    }

    private fun handleNavigateToPortfolioDetails(uiEvent: PortfolioViewEvent.NavigateToPortfolioDetails) {
        val action = PortfolioFragmentDirections.actionPortfolioFragmentToPortfolioDetailsFragment(
            uiEvent.portfolioItemListArgs
        )
        findNavController().navigate(action)
    }

    @Composable
    fun PortfolioIntegration(
        viewModel: PortfolioViewModel
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is PortfolioViewEvent.NavigateToAddCase -> handleNavigateToAddCase()
                    is PortfolioViewEvent.NavigateToSorting -> handleNavigateToSorting()
                    is PortfolioViewEvent.NavigateToPortfolioDetails -> handleNavigateToPortfolioDetails(
                        event
                    )

                    is PortfolioViewEvent.AnimateBarChart -> {

                    }
                }
            }
        }
        PortfolioScreen(
            state = state,
            onDetailsClicked = { viewModel.handleAction(PortfolioViewAction.OnPortfolioDetailsClicked) },
            onAddCaseClicked = { viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked) },
            onSortingClicked = { viewModel.handleAction(PortfolioViewAction.OnSortClicked) },
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}


