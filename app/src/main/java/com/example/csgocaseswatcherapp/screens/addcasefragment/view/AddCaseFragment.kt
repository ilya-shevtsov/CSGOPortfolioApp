package com.example.csgocaseswatcherapp.screens.addcasefragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentAddCaseBinding
import com.example.csgocaseswatcherapp.screens.addcasefragment.view.entities.AddedCaseModel
import kotlinx.coroutines.launch

class AddCaseFragment : Fragment(R.layout.fragment_add_case) {

    private val viewModel: AddCaseViewModel by viewModels()

    private lateinit var binding: FragmentAddCaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.uiState.collect { uiState ->
//
//                    }
//                }
//            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiEvent.collect { uiEvent ->
                        handleEvent(uiEvent)
                    }
                }
            }

            val caseNameList = listOf(
                "Chroma Case",
                "Chroma 2 Case",
                "Chroma 3 Case",
                "Clutch Case",
                "CSGO Weapon Case",
                "CSGO Weapon Case 2",
                "CSGO Weapon Case 3",
                "CS20 Case",
                "Danger Zone Case",
                "eSports 2013 Case",
                "eSports 2013 Winter Case",
                "eSports 2014 Summer Case",
                "Falchion Case",
                "Fracture Case",
                "Gamma Case",
                "Gamma 2 Case",
                "Glove Case",
                "Horizon Case",
                "Huntsman Weapon Case",
                "Operation Bravo Case",
                "Operation Breakout Weapon Case",
                "Operation Broken Fang Case",
                "Operation Hydra Case",
                "Operation Phoenix Weapon Case",
                "Operation Vanguard Weapon Case",
                "Operation Wildfire Case",
                "Prisma Case",
                "Prisma 2 Case",
                "Revolver Case",
                "Shadow Case",
                "Shattered Web Case",
                "Spectrum Case",
                "Spectrum 2 Case",
                "Winter Offensive Weapon Case",
                "Snakebite Case",
                "Dreams & Nightmares Case",
                "Recoil Case"
            )

            val caseNameArrayAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                caseNameList
            )

            addCaseName.threshold = 1

            addCaseName.setAdapter(caseNameArrayAdapter)

            addCaseButton.setOnClickListener {
                val addedCase = AddedCaseModel(
                    name = addCaseName.text.toString(),
                    amount = addCaseAmount.text.toString().toInt(),
                    purchasePrice = addCasePurchasePrice.text.toString().toDouble()
                )
                viewModel.handleAction(AddCaseViewAction.OnCaseAddedClicked(addedCase))
            }
        }
    }

    private fun handleEvent(uiEvent: AddCaseViewEvent) {
        when (uiEvent) {
            is AddCaseViewEvent.NavigateToPortfolioWithAddedCase -> {
                navigateToPortfolioWithAddedCase(uiEvent)
            }
        }
    }

    private fun navigateToPortfolioWithAddedCase(uiEvent: AddCaseViewEvent.NavigateToPortfolioWithAddedCase) {
        setFragmentResult(
            "addedCase",
            bundleOf("addedCase" to uiEvent.addedCase)
        )
        findNavController().popBackStack()
    }
}