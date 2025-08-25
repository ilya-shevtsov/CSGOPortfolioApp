package com.example.csgocaseswatcherapp.features.start.view

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
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

        composeView.setContent {
            AppTheme(dynamicColor = false) {
                StartScreenIntegration(viewModel)
            }
        }
    }

    @Composable
    fun StartScreenIntegration(
        viewModel: StartViewModel,
    ) {

        setFragmentResultListener("preferredCurrency") { _, bundle ->
            val preferredCurrency = bundle.getString("preferredCurrency")
            viewModel.handleAction(StartViewAction.OnCurrencySelected(preferredCurrency))
        }

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    StartViewEvent.NavigateToCaseOverview -> navigateTo(R.id.caseOverviewFragment)
                    StartViewEvent.NavigateToPortfolio -> navigateTo(R.id.portfolioFragment)
                    StartViewEvent.NavigateToAnalytics -> navigateTo(R.id.caseAnalyticsFragment)
                    StartViewEvent.NavigateToCurrencyChange -> navigateTo(R.id.currencyChangeFragment)
                }
            }
        }

        StartScreen(
            state = state,
            onCaseOverviewClicked = { viewModel.handleAction(StartViewAction.OnCaseOverviewClicked) },
            onCaseAnalyticsClicked = { viewModel.handleAction(StartViewAction.OnAnalyticsClicked) },
            onPortfolioClicked = { viewModel.handleAction(StartViewAction.OnPortfolioClicked) },
            onCurrencyClicked = { viewModel.handleAction(StartViewAction.OnCurrencyChangeClicked) }
        )
    }

    private fun navigateTo(distinction: Int) {
        findNavController().navigate(distinction)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication).getAppComponent().inject(this)
    }
}


