package com.example.csgocaseswatcherapp.presentation.view.fragments.caseoverview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.disposeOnDestroy
import com.example.csgocaseswatcherapp.databinding.FragmentCaseOverviewBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseOverviewFragment : Fragment(R.layout.fragment_case_overview) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCaseOverviewBinding

    private lateinit var viewModel: CaseOverviewViewModel

    private val adapter: CaseOverviewAdapter = CaseOverviewAdapter(onItemClicked = { case ->
        val action =
            CaseOverviewFragmentDirections.actionCaseOverviewFragmentToCaseDetailsFragment(case)
        findNavController().navigate(action)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaseOverviewBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CaseOverviewViewModel::class.java)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            caseRecyclerView.layoutManager = LinearLayoutManager(activity)
            caseRecyclerView.adapter = adapter

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect { uiState ->
                        handleState(uiState)

                    }
                }
            }
        }
        viewModel.getCaseList().disposeOnDestroy(viewLifecycleOwner)
    }

    private fun handleState(uiState: CaseOverviewViewState) {
        when (uiState) {
            is CaseOverviewViewState.Loading -> binding.loadingView.root.isVisible = true
            is CaseOverviewViewState.Error -> binding.errorView.root.isVisible = true
            is CaseOverviewViewState.Success -> {
                adapter.addData(uiState.caseOverviewItemList, true)
                binding.caseRecyclerView.isVisible = true
            }
        }
    }
}
