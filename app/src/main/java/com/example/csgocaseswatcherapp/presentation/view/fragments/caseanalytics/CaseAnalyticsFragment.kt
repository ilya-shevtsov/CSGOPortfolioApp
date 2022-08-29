package com.example.csgocaseswatcherapp.presentation.view.fragments.caseanalytics

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
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.databinding.FragmentCaseAnalyticsBinding
import com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem.CaseAnalyticsGroupieItem
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseAnalyticsFragment : Fragment(R.layout.fragment_case_analytics) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCaseAnalyticsBinding

    private val viewModel: CaseAnalyticsViewModel by viewModels { viewModelFactory }

    private val caseAnalyticsListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            caseAnalyticsRecyclerView.adapter = caseAnalyticsListAdapter

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

            caseAnalyticsListAdapter.setOnItemClickListener { caseOverViewListItem, _ ->
                when (caseOverViewListItem) {
                    is CaseAnalyticsGroupieItem -> {
                        viewModel.handleAction(
                            CaseAnalyticsViewAction.OnCaseClicked(
                                caseOverViewListItem.caseAnalyticsModel
                            )
                        )
                    }
                }
            }
        }
    }

    private fun handleEvent(uiEvent: CaseAnalyticsViewEvent) {
        when (uiEvent) {
            is CaseAnalyticsViewEvent.NavigateToCaseAnalyticsDetails -> navigateToCaseAnalyticsDetails(
                uiEvent
            )
        }
    }

    private fun navigateToCaseAnalyticsDetails(uiEvent: CaseAnalyticsViewEvent.NavigateToCaseAnalyticsDetails) {
        val action =
            CaseAnalyticsFragmentDirections.actionCaseAnalyticsFragmentToCaseAnalyticsDetailsFragment(
                uiEvent.case
            )
        findNavController().navigate(action)
    }

    private fun handleState(uiState: CaseAnalyticsViewState) {
        when (uiState) {
            is CaseAnalyticsViewState.Loading -> binding.loadingView.root.isVisible = true
            is CaseAnalyticsViewState.Error -> binding.errorView.root.isVisible = true
            is CaseAnalyticsViewState.Content -> {
                val caseAnalyticsViewItemList =
                    uiState.caseAnalyticsItemList.map { caseAnalyticsItem ->
                        CaseAnalyticsGroupieItem(caseAnalyticsItem)
                    }
                caseAnalyticsListAdapter.update(caseAnalyticsViewItemList)
                binding.caseAnalyticsRecyclerView.isVisible = true
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