package com.example.csgocaseswatcherapp.features.sortingbottomsheetfragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.databinding.FragmentSortingBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class SortingBottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: SortingBottomSheetFragmentViewModel by viewModels()

    private lateinit var binding: FragmentSortingBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSortingBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiEvent.collect { uiEvent ->
                        handleEvent(uiEvent)
                    }
                }
            }

            sortByName.setOnClickListener {
                val sortingMethod = SortingMethod.ByName
                viewModel.handleAction(
                    SortingBottomSheetFragmentViewAction.OnSortingMethodSelected(
                        sortingMethod
                    )
                )
            }

            sortByAmount.setOnClickListener {
                val sortingMethod = SortingMethod.ByAmount
                viewModel.handleAction(
                    SortingBottomSheetFragmentViewAction.OnSortingMethodSelected(
                        sortingMethod
                    )
                )
            }

            sortByPrice.setOnClickListener {
                val sortingMethod = SortingMethod.ByPrice
                viewModel.handleAction(
                    SortingBottomSheetFragmentViewAction.OnSortingMethodSelected(
                        sortingMethod
                    )
                )
            }

            sortByOverallValue.setOnClickListener {
                val sortingMethod = SortingMethod.ByOverallValue
                viewModel.handleAction(
                    SortingBottomSheetFragmentViewAction.OnSortingMethodSelected(
                        sortingMethod
                    )
                )
            }

            sortByProfitLoss.setOnClickListener {
                val sortingMethod = SortingMethod.ByProfitLoss
                viewModel.handleAction(
                    SortingBottomSheetFragmentViewAction.OnSortingMethodSelected(
                        sortingMethod
                    )
                )
            }
        }
    }

    private fun handleEvent(uiEvent: SortingBottomSheetFragmentViewEvent) {
        when (uiEvent) {
            is SortingBottomSheetFragmentViewEvent.NavigateToPortfolioWithSelectedSortingMethod -> {
                navigateToPortfolioWithSortMethod(uiEvent)
            }
        }
    }

    private fun navigateToPortfolioWithSortMethod(uiEvent: SortingBottomSheetFragmentViewEvent.NavigateToPortfolioWithSelectedSortingMethod) {
        setFragmentResult(
            "sortingMethod",
            bundleOf("sortingMethod" to uiEvent.sortingMethod)
        )
        findNavController().popBackStack()
    }
}