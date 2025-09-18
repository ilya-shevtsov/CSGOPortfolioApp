package com.example.csgocaseswatcherapp.features.caseanalytics.view

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
import androidx.compose.ui.platform.ViewCompositionStrategy
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
import com.example.csgocaseswatcherapp.databinding.FragmentCaseAnalyticsBinding
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsGroupieItem
import com.example.csgocaseswatcherapp.features.caseanalytics.view.entities.CaseAnalyticsModel
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewEvent
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseAnalyticsFragment : Fragment(R.layout.fragment_case_analytics) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaseAnalyticsViewModel by viewModels { viewModelFactory }

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
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        composeView.setContent {
            AppTheme {
                CaseAnalyticsIntegration(
                    viewModel = viewModel,
                )
            }
        }
    }

    @Composable
    fun CaseAnalyticsIntegration(
        viewModel: CaseAnalyticsViewModel,
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        CaseAnalyticsScreen(
            state = state,
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}