package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalyticsdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.databinding.FragmentCaseAnalyticsDetailsBinding
import com.facebook.drawee.drawable.Rounded
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseAnalyticsDetailsFragment : Fragment(R.layout.fragment_case_analytics_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaseAnalyticsDetailsViewModel by viewModels { viewModelFactory }

    private val args by navArgs<CaseAnalyticsDetailsFragmentArgs>()

    private lateinit var binding: FragmentCaseAnalyticsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseAnalyticsDetailsBinding.inflate(inflater, container, false)
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
        }
        viewModel.onItemProvided(args.currentCase)
    }

    private fun handleState(uiState: CaseAnalyticsDetailsViewState) {
        when (uiState) {
            is CaseAnalyticsDetailsViewState.Loading -> binding.loadingView.root.isVisible = true
            is CaseAnalyticsDetailsViewState.Content -> {
                with(binding) {
                    with(uiState.caseAnalyticsModel) {
                        caseNameTextView.text = caseName
                        caseImageView.setImageURI(imageUrl)
                        dailyAvgReturnInPercentDetails.text =
                            getString(
                                R.string.daily_avg_return_in_percent,
                                dailyAvgReturnInPercent.toString()
                            )
                        dailyAvgReturnInRubDetails.text =
                            getString(
                                R.string.daily_avg_return_in_rub,
                                dailyAvgReturnInRUB.toString()
                            )
                        dailyStandardDeviationDetails.text =
                            getString(
                                R.string.daily_standard_deviation,
                                dailyStandardDeviation.toString()
                            )
                        dailySharpRatioDetails.text =
                            getString(
                                R.string.daily_sharp_ratio,
                                dailySharpRatio.toString()
                            )
                        monthlyAvgReturnInPercentDetails.text = getString(
                            R.string.monthly_avg_return_in_percent,
                            monthlyAvgReturnInPercent.toString()
                        )
                        monthlyAvgReturnInRubDetails.text =
                            getString(
                                R.string.monthly_avg_return_in_rub,
                                monthlyAvgReturnInRUB.toString()
                            )
                        monthlyStandardDeviationDetails.text =
                            getString(
                                R.string.monthly_standard_deviation,
                               monthlyStandardDeviation.toString()
                            )
                        monthlySharpRatioDetails.text =
                            getString(
                                R.string.monthly_sharp_ratio,
                                monthlySharpRatio.toString()
                            )
                    }
                }

            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}