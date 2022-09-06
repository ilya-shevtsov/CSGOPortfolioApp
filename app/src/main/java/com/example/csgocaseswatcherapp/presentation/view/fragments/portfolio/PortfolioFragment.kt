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
import com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment.SortingMethod
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
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

            binding.barChartPortfolioValue

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

            setFragmentResultListener("sortingMethod") { _, bundle ->
                val sortingMethod = bundle.getSerializable("sortingMethod") as SortingMethod
                viewModel.handleAction(PortfolioViewAction.OnSortingMethodSelected(sortingMethod))
            }

            detailsButton.setOnClickListener {
                findNavController().navigate(R.id.portfolioDetailsFragment)
            }

            addCaseButton.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked)
            }

            buttonSorting.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnSortClicked)
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
                binding.itemCaseRecyclerView.smoothScrollToPosition(0)

                // Bar Chart

                binding.barChartPortfolioValue.description.isEnabled = false
                binding.barChartPortfolioValue.legend.isEnabled = false

                val newDataList = listOf(
                    BarEntry(1f, 129f),
                    BarEntry(2f, 164f),
                    BarEntry(3f, 225f),
                    BarEntry(4f, 236f),
                    BarEntry(5f, 334f),
                    BarEntry(6f, 479f),
                    BarEntry(7f, 429f),
                    BarEntry(8f, 424f),
                    BarEntry(9f, 448f),
                    BarEntry(10f, 335f),
                    BarEntry(11f, 315f),
                    BarEntry(12f, 322f),
                    BarEntry(13f, 414f),
                    BarEntry(14f, 458f),
                    BarEntry(15f, 509f),
                    BarEntry(16f, 546f),
                    BarEntry(17f, 668f),
                    BarEntry(18f, 741f),
                    BarEntry(19f, 685f),
                    BarEntry(20f, 840f),
                    BarEntry(21f, 834f),
                )

                val dataSet = BarDataSet(newDataList, "Portfolio Value")
                dataSet.color = Color.parseColor("#2FA1BA")

                val data = BarData(dataSet)
                data.setDrawValues(true)
                data.setValueTextSize(10f)
                data.setValueTextColor(Color.BLACK)

                binding.barChartPortfolioValue.data = data
                binding.barChartPortfolioValue.invalidate()
                binding.barChartPortfolioValue.animateY(1400, Easing.EaseInOutQuad)

//                binding.pieChartPortfolioValue.isDrawHoleEnabled = true
//                binding.pieChartPortfolioValue.setUsePercentValues(true)
//                binding.pieChartPortfolioValue.setEntryLabelTextSize(10F)
//                binding.pieChartPortfolioValue.setEntryLabelColor(Color.BLACK)
//                binding.pieChartPortfolioValue.centerText = "Amount"
//                binding.pieChartPortfolioValue.setCenterTextSize(24F)
//                binding.pieChartPortfolioValue.description.isEnabled = false
//                binding.pieChartPortfolioValue.legend.isEnabled = false
//
//
//                val colors = arrayListOf<Int>()
//                for (color in ColorTemplate.MATERIAL_COLORS) {
//                    colors.add(color)
//                }
//                for (color in ColorTemplate.VORDIPLOM_COLORS) {
//                    colors.add(color)
//                }
//
//                val dataSet = PieDataSet(uiState.portfolioPietEntryList, "Cases by amount")
//                dataSet.colors = colors
//
//                val data = PieData(dataSet)
//                data.setDrawValues(true)
//                data.setValueFormatter(PercentFormatter(binding.pieChartPortfolioValue))
//                data.setValueTextSize(10f)
//                data.setValueTextColor(Color.BLACK)
//
//                binding.pieChartPortfolioValue.data = data
//                binding.pieChartPortfolioValue.invalidate()
//                binding.pieChartPortfolioValue.animateY(1400, Easing.EaseInOutQuad)
            }
        }
    }

    private fun handleEvent(uiEvent: PortfolioViewEvent) {
        when (uiEvent) {
            PortfolioViewEvent.NavigateToAddCase -> findNavController().navigate(R.id.addCaseFragment)
            PortfolioViewEvent.NavigateToSorting -> findNavController().navigate(R.id.sortingBottomSheetFragment)
        }
    }
}


