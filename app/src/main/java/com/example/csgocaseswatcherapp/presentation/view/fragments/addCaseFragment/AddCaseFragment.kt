package com.example.csgocaseswatcherapp.presentation.view.fragments.addCaseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentAddCaseBinding
import com.example.csgocaseswatcherapp.presentation.model.addcaseitem.AddedCaseModel
import kotlinx.coroutines.launch

class AddCaseFragment : Fragment(R.layout.fragment_add_case) {

//    private val viewModel: AddCaseViewModel by viewModels()

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

//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.uiEvent.collect { uiEvent ->
//                        handleEvent(uiEvent)
//                    }
//                }
//            }

            val caseNameArray = listOf("Chroma Case",
                "Chroma 2",
                "Chroma 3",
                "Clutch Case",
                "CSGO Weapon",
                "CSGO Weapon",
                "CSGO Weapon",
                "CS20 Case",
                "Danger Zone",
                "eSports 2013",
                "eSports 2013",
                "eSports 2014",
                "Falchion Case",
                "Fracture Case",
                "Gamma Case",
                "Gamma 2",
                "Glove Case",
                "Horizon Case",
                "Huntsman Weapon",
                "Operation Bravo",
                "Operation Breakout",
                "Operation Broken",
                "Operation Hydra",
                "Operation Phoenix",
                "Operation Vanguard",
                "Operation Wildfire",
                "Prisma Case",
                "Prisma 2",
                "Revolver Case",
                "Shadow Case",
                "Shattered Web",
                "Spectrum Case",
                "Spectrum 2",
                "Winter Offensive",
                "Snakebite Case",
                "Dreams & Nightmares Case",
                "Recoil Case")

            val caseNameArrayAdapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    caseNameArray
                )

            addCaseCaseName.threshold = 1

            addCaseCaseName.setAdapter(caseNameArrayAdapter)

            addCaseButton.setOnClickListener {
                val addedCase = AddedCaseModel(
                    name = addCaseCaseName.text.toString(),
                    amount = addCaseCaseAmount.text.toString().toInt(),
                    purchasePrice = addCaseCasePurchasePrice.text.toString().toDouble()
                )
                setFragmentResult(
                    "addedCase",
                    bundleOf("addedCase" to addedCase)
                )
                findNavController().popBackStack()

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