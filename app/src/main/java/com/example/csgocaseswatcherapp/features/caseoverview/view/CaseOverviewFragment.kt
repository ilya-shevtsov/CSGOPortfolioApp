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
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
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

    private val viewModel: CaseOverviewViewModel by viewModels { viewModelFactory }

    private lateinit var composeView: ComposeView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        composeView.setViewCompositionStrategy(
            androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        composeView.setContent {
            AppTheme(dynamicColor = false) {
                CaseOverviewIntegration(
                    viewModel = viewModel,
                    onNavigateToDetails = { model -> navigateToDetails(model)

                    }
                )
            }
        }
    }

    private fun navigateToDetails(model:CaseOverviewModel){
        val action =
            CaseOverviewFragmentDirections.actionCaseOverviewFragmentToCaseDetailsFragment(
                model
            )
        findNavController().navigate(action)
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
