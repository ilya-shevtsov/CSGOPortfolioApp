package com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view

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
import com.example.csgocaseswatcherapp.databinding.FragmentCaseOverviewBinding
import com.example.csgocaseswatcherapp.presentation.screens.caseoverview.view.entities.CaseOverviewGroupieItem
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseOverviewFragment : Fragment(R.layout.fragment_case_overview) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCaseOverviewBinding

    private val viewModel: CaseOverviewViewModel by viewModels { viewModelFactory }

    private val caseOverviewListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            caseRecyclerView.adapter = caseOverviewListAdapter

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

            caseOverviewListAdapter.setOnItemClickListener { caseOverViewListItem, _ ->
                when (caseOverViewListItem) {
                    is CaseOverviewGroupieItem -> {
                        viewModel.handleAction(
                            CaseOverviewViewAction.OnCaseClicked(
                                caseOverViewListItem.caseOverviewModel
                            )
                        )
                    }
                }
            }
        }
    }

    private fun handleEvent(uiEvent: CaseOverviewViewEvent) {
        when (uiEvent) {
            is CaseOverviewViewEvent.NavigateToCaseDetails -> navigateToCaseDetails(uiEvent)
        }
    }

    private fun navigateToCaseDetails(uiEvent: CaseOverviewViewEvent.NavigateToCaseDetails) {
        val action =
            CaseOverviewFragmentDirections.actionCaseOverviewFragmentToCaseDetailsFragment(
                uiEvent.case
            )
        findNavController().navigate(action)
    }

    private fun handleState(uiState: CaseOverviewViewState) {
        with(binding) {
            when (uiState) {
                is CaseOverviewViewState.Loading -> loadingView.root.isVisible = true
                is CaseOverviewViewState.Error -> errorView.root.isVisible = true
                is CaseOverviewViewState.Content -> {
                    val caseOverViewItemList =
                        uiState.caseOverviewItemList.map { caseOverViewItem ->
                            CaseOverviewGroupieItem(caseOverViewItem)
                        }
                    caseOverviewListAdapter.update(caseOverViewItemList)
                    caseRecyclerView.isVisible = true
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
