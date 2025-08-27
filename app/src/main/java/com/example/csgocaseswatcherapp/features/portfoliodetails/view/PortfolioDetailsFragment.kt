package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioDetailsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch
import javax.inject.Inject

class PortfolioDetailsFragment : Fragment(R.layout.fragment_portfolio_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PortfolioDetailsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentPortfolioDetailsBinding

    private val args by navArgs<PortfolioDetailsFragmentArgs>()

    private lateinit var composeView: ComposeView

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ):View {
//        return ComposeView(requireContext()).also {
//            composeView = it
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        composeView.setViewCompositionStrategy(
//            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
//        )
//        composeView.setContent {
//            AppTheme(dynamicColor = false) {
//                PortfolioDetailsIntegration(viewModel)
//            }
//        }

        binding.pieChartPortfolioValue

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    handleState(uiState)
                }
            }
        }
        viewModel.handleAction(PortfolioDetailsViewAction.OnPortfolioDataProvided(args.portfolioItemListArgs))
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

    @Composable
    fun PortfolioDetailsIntegration(
        viewModel: PortfolioDetailsViewModel
    ) {
        viewModel.handleAction(PortfolioDetailsViewAction.OnPortfolioDataProvided(args.portfolioItemListArgs))

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        PortfolioDetailsScreen(
            state = state,

            )
    }

    @Composable
    fun PortfolioDetailsScreen(
        state: PortfolioDetailsViewState
    ) {
        when (state) {
            is PortfolioDetailsViewState.Error -> ErrorScreen()
            is PortfolioDetailsViewState.Loading -> LoadingScreen()
            is PortfolioDetailsViewState.Content -> PortfolioDetailsContent(
                portfolioPietEntryList = state.portfolioPietEntryList,
                modifier = Modifier
            )

        }
    }

    @Composable
    fun PortfolioDetailsContent(
        portfolioPietEntryList: List<PieEntry>, modifier: Modifier
    ) {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                PieChart(context).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                    description = Description().apply { text = "" }
                    legend.isEnabled = false

                    isDrawHoleEnabled = true
                    setUsePercentValues(true)
                    setEntryLabelTextSize(10f)
                    setEntryLabelColor(Color.BLACK)
                    setCenterTextSize(24f)
                    centerText = centerText

                }
            },
            update = { chart ->
                val dataSet = PieDataSet(portfolioPietEntryList, "Amount"). apply {
                    this.colors = colors
                }

                val data = PieData(dataSet).apply {
                    setDrawValues(true)
                    setValueTextSize(10f)
                    setValueTextColor(Color.BLACK)
                }

                chart.data = data
                chart.invalidate()
                chart.animateY(1400, Easing.EaseInOutQuad)
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}

