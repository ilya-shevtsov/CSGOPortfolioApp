package com.example.csgocaseswatcherapp.features.caseoverview.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.databinding.FragmentCaseOverviewBinding
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewGroupieItem
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.entities.CaseOverviewScreen
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseOverviewFragment : Fragment(R.layout.fragment_case_overview) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCaseOverviewBinding

    private val viewModel: CaseOverviewViewModel by viewModels { viewModelFactory }

    private lateinit var composeView: ComposeView

    private val caseOverviewListAdapter = GroupieAdapter()


    //    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).also {
//            composeView = it
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        composeView.setViewCompositionStrategy(
//            androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
//        )

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

    @Composable
    fun CaseOverviewIntegration(
        viewModel: CaseOverviewViewModel,
        onNavigateToDetails: (CaseOverviewModel) -> Unit,
    ) {

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is CaseOverviewViewEvent.NavigateToCaseDetails -> onNavigateToDetails(
                        event.case
                    )
                }
            }
        }

        CaseOverviewScreen(
            state = state,
            onCaseClick = { clicked ->
                viewModel.handleAction(CaseOverviewViewAction.OnCaseClicked(clicked))
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
