package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.AddCaseItem
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

            itemCaseRecyclerView.adapter = caseListAdapter

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect{ uiState ->
                        val portfolioCaseItemList = uiState.portfolioItemList
                        caseListAdapter.update(portfolioCaseItemList)

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

                val addedCase = bundle.getSerializable("addedCase") as AddCaseItem
                viewModel.handleAction(PortfolioViewAction.OnCaseAdded(addedCase))
            }

            homeButton.setOnClickListener {
                findNavController().navigate(R.id.startFragment)
            }

            addCaseButton.setOnClickListener {
                viewModel.handleAction(PortfolioViewAction.OnAddCaseClicked)
            }
        }
    }

    private fun handleEvent(uiEvent: PortfolioViewEvent) {
        when (uiEvent) {
            PortfolioViewEvent.NavigateToAddCase -> findNavController().navigate(R.id.addCaseFragment)
        }
    }
}


