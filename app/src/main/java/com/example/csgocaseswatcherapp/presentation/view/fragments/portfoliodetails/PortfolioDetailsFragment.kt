package com.example.csgocaseswatcherapp.presentation.view.fragments.portfoliodetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioDetailsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch


class PortfolioDetailsFragment : Fragment(R.layout.fragment_portfolio_details) {

    private val viewModel: PortfolioDetailsViewModel by viewModels()

    private lateinit var binding: FragmentPortfolioDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        with(binding) {
//
//            binding.barChartPortfolioValue
//
//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.uiState.collect { uiState ->
//                        handleState(uiState)
//                    }
//                }
//            }
//
//
////            lifecycleScope.launch {
////                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
////                    viewModel.uiEvent.collect { uiEvent ->
////                        handleEvent(uiEvent)
////                    }
////                }
////            }
//        }
//    }
//
//    private fun handleState(uiState: PortfolioDetailsViewState) {
//        when (uiState) {
//            is PortfolioDetailsViewState.Loading -> binding.loadingView.root.isVisible = true
//            is PortfolioDetailsViewState.Error -> {
//                binding.loadingView.root.isVisible = false
//                binding.errorView.root.isVisible = true
//            }
//            is PortfolioDetailsViewState.Content -> {
//                binding.loadingView.root.isVisible = false

//            }
//        }
//    }
//
////    private fun handleEvent(uiEvent: PortfolioDetailsViewEvent) {
////        when (uiEvent) {
////
////        }
////    }
//}
//
