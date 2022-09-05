package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch


class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    private val viewModel: PortfolioViewModel by viewModels()

    private lateinit var binding: FragmentPortfolioBinding

    private val caseListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            binding.pieChartPortfolioValue

            itemCaseRecyclerView.adapter = caseListAdapter

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

            setFragmentResultListener("addedCase") { _, bundle ->
                val addedCase = bundle.getSerializable("addedCase") as AddedCaseModel
                viewModel.handleAction(PortfolioViewAction.OnCaseAdded(addedCase))
            }

            addCaseButton.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked)
            }

            caseNameHeader.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnCaseNameSortClicked)
            }

            caseAmountHeader.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnCaseAmountClicked)
            }

            casePriceHeader.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnCasePriceClicked)
            }

            caseOverallValueHeader.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnCaseOverallValueClicked)
            }

            caseProfitLossHeader.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnCaseProfitLossClicked)
            }
        }
    }

    private fun handleState(uiState: PortfolioViewState) {
        when (uiState) {
            is PortfolioViewState.Loading -> binding.loadingView.root.isVisible = true
            is PortfolioViewState.Error -> {
                binding.loadingView.root.isVisible = false
                binding.errorView.root.isVisible = true
            }
            is PortfolioViewState.Content -> {
                binding.totalValue.text = binding.root.context.getString(
                    R.string.portfolio_total_value, uiState.totalPortfolioValue.toString()
                )
                binding.loadingView.root.isVisible = false
                caseListAdapter.update(uiState.portfolioItemList)
                binding.itemCaseRecyclerView.isVisible = true

                /////

                binding.pieChartPortfolioValue.isDrawHoleEnabled = true
                binding.pieChartPortfolioValue.setUsePercentValues(true)
                binding.pieChartPortfolioValue.setEntryLabelTextSize(10F)
                binding.pieChartPortfolioValue.setEntryLabelColor(Color.BLACK)
                binding.pieChartPortfolioValue.centerText = "Amount"
                binding.pieChartPortfolioValue.setCenterTextSize(24F)
                binding.pieChartPortfolioValue.description.isEnabled = false
                binding.pieChartPortfolioValue.legend.isEnabled = false


                val colors = arrayListOf<Int>()
                for (color in ColorTemplate.MATERIAL_COLORS) {
                    colors.add(color)
                }
                for (color in ColorTemplate.VORDIPLOM_COLORS) {
                    colors.add(color)
                }

                val dataSet = PieDataSet(uiState.portfolioPietEntryList, "Cases by amount")
                dataSet.colors = colors

                val data = PieData(dataSet)
                data.setDrawValues(true)
                data.setValueFormatter(PercentFormatter(binding.pieChartPortfolioValue))
                data.setValueTextSize(10f)
                data.setValueTextColor(Color.BLACK)

                binding.pieChartPortfolioValue.data = data
                binding.pieChartPortfolioValue.invalidate()
                binding.pieChartPortfolioValue.animateY(1400, Easing.EaseInOutQuad)
            }
        }
    }

    private fun handleEvent(uiEvent: PortfolioViewEvent) {
        when (uiEvent) {
            PortfolioViewEvent.NavigateToAddCase -> findNavController().navigate(R.id.addCaseFragment)
        }
    }
}


