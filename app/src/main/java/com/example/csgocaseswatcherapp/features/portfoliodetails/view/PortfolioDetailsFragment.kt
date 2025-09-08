package com.example.csgocaseswatcherapp.features.portfoliodetails.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioDetailsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
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

    private val args by navArgs<PortfolioDetailsFragmentArgs>()

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
        composeView.setContent {
            AppTheme {
                PortfolioDetailsIntegration(viewModel)
            }
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
        portfolioPietEntryList: List<PieEntry>,
        modifier: Modifier = Modifier
    ) {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                PieChart(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT
                    )

                    isDrawHoleEnabled = true
                    setUsePercentValues(true)
                    setEntryLabelTextSize(10f)
                    setEntryLabelColor(Color.BLACK)
                    centerText = "Amount"
                    setCenterTextSize(24f)

                    description.isEnabled = false
                    legend.isEnabled = false
                }
            },
            update = { chart ->
                val colors = mutableListOf<Int>().apply {
                    addAll(ColorTemplate.MATERIAL_COLORS.toList())
                    addAll(ColorTemplate.VORDIPLOM_COLORS.toList())
                }

                val dataSet = PieDataSet(portfolioPietEntryList, "Cases by amount").apply {
                    this.colors = colors
                }

                val data = PieData(dataSet).apply {
                    setDrawValues(true)
                    setValueFormatter(PercentFormatter(chart))
                    setValueTextSize(10f)
                    setValueTextColor(Color.BLACK)
                }

                chart.data = data
                chart.invalidate()
                chart.animateY(1400, Easing.EaseInOutQuad)
            }
        )
    }

    @Preview
    @Composable
    fun PortfolioDetailsScreenPreview() {
        PortfolioDetailsScreen(
            state = PortfolioDetailsViewState.Content(
                portfolioPietEntryList = listOf(
                    PieEntry(0.0f, 255.0f),
                    PieEntry(0.0f, 20.0f),
                    PieEntry(0.0f, 1.0f),
                    PieEntry(0.0f, 10.0f),
                    PieEntry(0.0f, 2.0f)
                )
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}

