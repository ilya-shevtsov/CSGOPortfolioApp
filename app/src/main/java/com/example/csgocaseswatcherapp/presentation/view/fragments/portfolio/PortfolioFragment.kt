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
import com.github.mikephil.charting.data.PieEntry
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.animation.Easing

import com.github.mikephil.charting.formatter.PercentFormatter

import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.components.Legend













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

            fun setPieChart(){
                pieChartPortfolioValue.isDrawHoleEnabled = true
                pieChartPortfolioValue.setUsePercentValues(true)
                pieChartPortfolioValue.setEntryLabelTextSize(12F)
                pieChartPortfolioValue.setEntryLabelColor(Color.BLACK)
                pieChartPortfolioValue.centerText = "Value"
                pieChartPortfolioValue.setCenterTextSize(24F)
                pieChartPortfolioValue.description.isEnabled = false

                val legend = pieChartPortfolioValue.legend
                legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                legend.orientation = Legend.LegendOrientation.VERTICAL
                legend.setDrawInside(false)
                legend.isEnabled = true

            }

            fun loadPieChartData(){

                val entries = arrayListOf<PieEntry>()
                entries.add(PieEntry(0.2f, "Chroma 2 Case"))
                entries.add(PieEntry(0.2f, "Clutch Case"))
                entries.add(PieEntry(0.2f, "Danger Zone Case"))

                val colors = arrayListOf<Int>()
                for (color in ColorTemplate.MATERIAL_COLORS) {
                    colors.add(color)
                }
                for (color in ColorTemplate.VORDIPLOM_COLORS) {
                    colors.add(color)
                }

                val dataSet = PieDataSet(entries, "Expense Category")
                dataSet.colors = colors

                val data = PieData(dataSet)
                data.setDrawValues(true)
                data.setValueFormatter(PercentFormatter(pieChartPortfolioValue))
                data.setValueTextSize(12f)
                data.setValueTextColor(Color.BLACK)

                pieChartPortfolioValue.data = data
                pieChartPortfolioValue.invalidate()

                pieChartPortfolioValue.animateY(1400, Easing.EaseInOutQuad)

            }

            setPieChart()
            loadPieChartData()

            itemCaseRecyclerView.adapter = caseListAdapter

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect{ uiState ->
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

            homeButton.setOnClickListener {
                findNavController().navigate(R.id.startFragment)
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
        when(uiState){
            is PortfolioViewState.Loading -> binding.loadingView.root.isVisible = true
            is PortfolioViewState.Error -> {
                binding.loadingView.root.isVisible = false
                binding.errorView.root.isVisible = true
            }
            is PortfolioViewState.Content -> {
                binding.loadingView.root.isVisible = false
                caseListAdapter.update(uiState.portfolioItemList)
                binding.itemCaseRecyclerView.isVisible = true
            }
        }


    }

    private fun handleEvent(uiEvent: PortfolioViewEvent) {
        when (uiEvent) {
            PortfolioViewEvent.NavigateToAddCase -> findNavController().navigate(R.id.addCaseFragment)
        }
    }
}


