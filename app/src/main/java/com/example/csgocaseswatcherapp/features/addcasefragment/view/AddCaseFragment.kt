package com.example.csgocaseswatcherapp.features.addcasefragment.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class AddCaseFragment : Fragment(R.layout.fragment_add_case) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddCaseViewModel by viewModels { viewModelFactory }

    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        composeView.setContent {
            AppTheme {
                AddCaseIntegration(viewModel)
            }
        }
    }


    @Composable
    fun AddCaseIntegration(viewModel: AddCaseViewModel) {

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is AddCaseViewEvent.NavigateToPortfolioWithAddedCase -> {
                        navigateToPortfolioWithAddedCase()
                    }
                    is AddCaseViewEvent.ShowValidationError -> showErrorMessage(event.message)
                }
            }
        }

        AddCaseScreen(
            state,
            onAction = { action -> viewModel.handleAction(action) },

            )
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToPortfolioWithAddedCase() {
        findNavController().popBackStack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }

    companion object {
        const val ADD_CASE_REQUEST_KEY = "addedCase"
    }
}