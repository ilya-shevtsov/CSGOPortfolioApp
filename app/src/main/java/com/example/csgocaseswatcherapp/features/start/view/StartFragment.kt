package com.example.csgocaseswatcherapp.features.start.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class StartFragment : Fragment(R.layout.fragment_start) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var composeView: ComposeView

    private val viewModel: StartViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView.setViewCompositionStrategy(
            androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        setFragmentResultListener("preferredCurrency") { _, bundle ->
            val preferredCurrency = bundle.getString("preferredCurrency")
            viewModel.handleAction(StartViewAction.OnCurrencySelected(preferredCurrency))
        }

        composeView.setContent {
            AppTheme(dynamicColor = false) {
                val state by viewModel.uiState.collectAsState()
                LaunchedEffect(Unit) {
                    viewModel.uiEvent.collectLatest { event ->
                        when (event) {
                            StartViewEvent.NavigateToCaseOverview -> navigateTo(R.id.caseOverviewFragment)
                            StartViewEvent.NavigateToPortfolio -> navigateTo(R.id.portfolioFragment)
                            StartViewEvent.NavigateToAnalytics -> navigateTo(R.id.caseAnalyticsFragment)
                            StartViewEvent.NavigateToCurrencyChange -> navigateTo(R.id.currencyChangeFragment)
                        }
                    }
                }

                when (state) {
                    is StartViewState.Content -> StartScreen(
                        state = state as StartViewState.Content,
                        onCaseOverviewClicked = { viewModel.handleAction(StartViewAction.OnCaseOverviewClicked) },
                        onCaseAnalyticsClicked = { viewModel.handleAction(StartViewAction.OnAnalyticsClicked) },
                        onPortfolioClicked = { viewModel.handleAction(StartViewAction.OnPortfolioClicked) },
                        onCurrencyClicked = { viewModel.handleAction(StartViewAction.OnCurrencyChangeClicked) }
                    )

                    is StartViewState.Error -> {}

                    is StartViewState.Loading -> {}
                }
            }
        }
    }

    private fun navigateTo(distinction: Int) {
        findNavController().navigate(distinction)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication).getAppComponent().inject(this)
    }

}
