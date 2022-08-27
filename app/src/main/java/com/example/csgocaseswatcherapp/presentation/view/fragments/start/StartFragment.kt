package com.example.csgocaseswatcherapp.presentation.view.fragments.start

import android.os.Bundle
import android.util.Log
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
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.data.model.prederredcurrencydto.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.databinding.FragmentStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

                    }
                }
            }

            setFragmentResultListener("preferredCurrency") { _, bundle ->

                val preferredCurrency = bundle.getString("bundleKey")

                viewModel.handleAction(StartViewAction.OnCurrencySelected(preferredCurrency))


                when (preferredCurrency) {
                    "USD" -> {
                        sendPreferredCurrency(PreferredCurrencyDto(1))
                        Log.e("ServerSide", "SendUSD")
                    }
                    "RUB" -> {
                        sendPreferredCurrency(PreferredCurrencyDto(5))
                        Log.e("ServerSide", "SendRUB")
                    }
                }
            }

            currencyChangeButton.setOnClickListener {
                findNavController().navigate(R.id.currencyChangeFragment)
            }

            binding.caseOverviewButton.setOnClickListener {
                findNavController().navigate(R.id.caseOverviewFragment)
            }

            binding.casePortfolioButton.setOnClickListener {
                findNavController().navigate(R.id.portfolioFragment)
            }
            binding.caseAnalyticsButton.setOnClickListener {
                findNavController().navigate(R.id.caseAnalyticsFragment)
            }
        }
    }

    private fun sendPreferredCurrency(preferredCurrency: PreferredCurrencyDto) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiTools.getApiService().postPreferredCurrency(preferredCurrency)
        }
    }
}