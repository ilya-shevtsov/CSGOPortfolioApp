package com.example.csgocaseswatcherapp.features.start.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.databinding.FragmentStartBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragment : Fragment(R.layout.fragment_start) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: StartViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect { uiState ->
                        handleState(uiState)
                    }
                }
            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiEvent.collect { uiEvent ->
                        handleEvent(uiEvent)
                    }
                }
            }

            setFragmentResultListener("preferredCurrency") { _, bundle ->
                val preferredCurrency = bundle.getString("preferredCurrency")
                viewModel.handleAction(StartViewAction.OnCurrencySelected(preferredCurrency))
            }

            caseOverviewButton.setOnClickListener {
                viewModel.handleAction(StartViewAction.OnCaseOverviewClicked)
            }

            casePortfolioButton.setOnClickListener {
                viewModel.handleAction(StartViewAction.OnPortfolioClicked)
            }

            caseAnalyticsButton.setOnClickListener {
                viewModel.handleAction(StartViewAction.OnAnalyticsClicked)
            }

            currencyChangeButton.setOnClickListener {
                viewModel.handleAction(StartViewAction.OnCurrencyChangeClicked)
            }
        }
    }

    private fun handleState(uiState: StartViewState) {
        with(binding) {
            when (uiState) {
                is StartViewState.Loading -> loadingView.root.isVisible = true
                is StartViewState.Error -> {
                    loadingView.root.isVisible = false
                    errorView.root.isVisible = true
                }
                is StartViewState.Content -> {
                    currencyChangeButton.text = uiState.currencyButton
                }
            }
        }
    }

    private fun handleEvent(uiEvent: StartViewEvent) {
        when (uiEvent) {
            StartViewEvent.NavigateToCaseOverview -> findNavController().navigate(R.id.caseOverviewFragment)
            StartViewEvent.NavigateToPortfolio -> findNavController().navigate(R.id.portfolioFragment)
            StartViewEvent.NavigateToAnalytics -> findNavController().navigate(R.id.caseAnalyticsFragment)
            StartViewEvent.NavigateToCurrencyChange -> findNavController().navigate(R.id.currencyChangeFragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}
