package com.example.csgocaseswatcherapp.presentation.screens.caseoverviewdetails.view

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
import com.example.csgocaseswatcherapp.databinding.FragmentCaseDetailsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseDetailsFragment : Fragment(R.layout.fragment_case_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaseDetailsViewModel by viewModels { viewModelFactory }

    private val args by navArgs<CaseDetailsFragmentArgs>()

    private lateinit var binding: FragmentCaseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseDetailsBinding.inflate(inflater, container, false)
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
        viewModel.handleAction(CaseDetailsViewAction.OnItemProvided(args.currentCase))
    }

    private fun handleState(uiState: CaseDetailsViewState) {
        when (uiState) {
            is CaseDetailsViewState.Loading -> binding.loadingView.root.isVisible = true
            is CaseDetailsViewState.Content -> {
                with(binding) {
                    with(uiState.caseOverviewModel) {
                        caseNameTextView.text = caseName
                        caseImageView.setImageURI(imageUrl)
                        lowestPriceTextView.text =
                            getString(R.string.case_lowest_price, lowestPrice.toString())
                        medianPriceTextView.text =
                            getString(R.string.case_median_price, medianPrice.toString())
                        volumeTextView.text = getString(R.string.case_volume, volume.toString())
                        releaseDateTextView.text =
                            getString(R.string.case_release_date, releaseDate)
                        dropStatusTextView.text =
                            getString(R.string.case_drop_status, dropStatus)
                        descriptionTextView.text = description
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
