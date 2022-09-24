package com.example.csgocaseswatcherapp.presentation.screens.portfoliodetails.view

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
import androidx.navigation.fragment.navArgs
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioDetailsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch

class PortfolioDetailsFragment : Fragment(R.layout.fragment_portfolio_details) {

    private val viewModel: PortfolioDetailsViewModel by viewModels()

    private lateinit var binding: FragmentPortfolioDetailsBinding

    private val args by navArgs<PortfolioDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            binding.pieChartPortfolioValue

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect { uiState ->
                        handleState(uiState)
                    }
                }
            }
            viewModel.handleAction(PortfolioDetailsViewAction.OnPortfolioDataProvided(args.portfolioItemListArgs))

//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.uiEvent.collect { uiEvent ->
//                        handleEvent(uiEvent)
//                    }
//                }
//            }
        }
    }

    private fun handleState(uiState: PortfolioDetailsViewState) {
        when (uiState) {
            is PortfolioDetailsViewState.Loading -> binding.loadingView.root.isVisible = true
            is PortfolioDetailsViewState.Error -> {
                binding.loadingView.root.isVisible = false
                binding.errorView.root.isVisible = true
            }
            is PortfolioDetailsViewState.Content -> {
                binding.loadingView.root.isVisible = false
                setUpChart(uiState.portfolioPietEntryList)
            }
        }
    }

//    private fun handleEvent(uiEvent: PortfolioDetailsViewEvent) {
//        when (uiEvent) {
//        }
//    }

    private fun setUpChart(dataEntries: List<PieEntry>) {

        with(binding) {

            pieChartPortfolioValue.isDrawHoleEnabled = true
            pieChartPortfolioValue.setUsePercentValues(true)
            pieChartPortfolioValue.setEntryLabelTextSize(10F)
            pieChartPortfolioValue.setEntryLabelColor(Color.BLACK)
            pieChartPortfolioValue.centerText = "Amount"
            pieChartPortfolioValue.setCenterTextSize(24F)
            pieChartPortfolioValue.description.isEnabled = false
            pieChartPortfolioValue.legend.isEnabled = false

            val colors = arrayListOf<Int>()
            for (color in ColorTemplate.MATERIAL_COLORS) {
                colors.add(color)
            }
            for (color in ColorTemplate.VORDIPLOM_COLORS) {
                colors.add(color)
            }

            val dataSet = PieDataSet(dataEntries, "Cases by amount")
            dataSet.colors = colors

            val data = PieData(dataSet)
            data.setDrawValues(true)
            data.setValueFormatter(PercentFormatter(pieChartPortfolioValue))
            data.setValueTextSize(10f)
            data.setValueTextColor(Color.BLACK)

            pieChartPortfolioValue.data = data
            pieChartPortfolioValue.invalidate()
            pieChartPortfolioValue.animateY(1400, Easing.EaseInOutQuad)
        }
    }
}

