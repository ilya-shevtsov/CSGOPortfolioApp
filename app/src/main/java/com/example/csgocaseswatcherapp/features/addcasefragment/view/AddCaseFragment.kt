package com.example.csgocaseswatcherapp.features.addcasefragment.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.databinding.FragmentAddCaseBinding
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddCaseScreen
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddedCaseModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCaseFragment : Fragment(R.layout.fragment_add_case) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddCaseViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentAddCaseBinding

    private lateinit var composeView: ComposeView

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).also {
//            composeView = it
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//
//        composeView.setContent {
//            AppTheme(dynamicColor = false) {
//                AddCaseIntegration(viewModel)
//            }
//        }

        with(binding) {

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
//                viewModel.handleAction(AddCaseViewAction.OnCaseAddedClicked(addedCase))
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

    @Composable
    fun AddCaseIntegration(viewModel: AddCaseViewModel) {

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is AddCaseViewEvent.NavigateToPortfolioWithAddedCase -> {
                        setFragmentResult(
                            "addedCase",
                            bundleOf("addedCase" to event.addedCase)
                        )
                    }
                }
            }
        }

        AddCaseScreen(
            state,
            onNameChanged = { newValue ->
                viewModel.handleAction(
                    AddCaseViewAction.OnNameChanged(
                        newValue
                    )
                )
            },
            onAmountChanged = { newValue ->
                viewModel.handleAction(
                    AddCaseViewAction.onAmountChanged(
                        newValue
                    )
                )
            },
            onPriceChanged = { newValue ->
                viewModel.handleAction(
                    AddCaseViewAction.onPriceChanged(
                        newValue
                    )
                )
            },

            onAddCaseClicked = { viewModel.handleAction(AddCaseViewAction.onAddCaseClicked) },

            onSuggestionClicked = { caseName ->
                viewModel.handleAction(
                    AddCaseViewAction.onSuggestionClicked(
                        caseName
                    )
                )
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