package com.example.csgocaseswatcherapp.presentation.view.fragments.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentStartBinding
import kotlinx.coroutines.launch

class StartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: StartViewModel by viewModels()

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
                        currencyChangeButton.text = uiState.currencyButton
                        viewModel.getPreferredCurrency()
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

    private fun handleEvent(uiEvent: StartViewEvent) {
        when (uiEvent) {
            StartViewEvent.NavigateToCaseOverview -> findNavController().navigate(R.id.caseOverviewFragment)
            StartViewEvent.NavigateToPortfolio ->  findNavController().navigate(R.id.portfolioFragment)
            StartViewEvent.NavigateToAnalytics ->  findNavController().navigate(R.id.caseAnalyticsFragment)
            StartViewEvent.NavigateToCurrencyChange ->  findNavController().navigate(R.id.currencyChangeFragment)
        }
    }
}