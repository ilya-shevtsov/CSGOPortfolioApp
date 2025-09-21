package com.example.csgocaseswatcherapp.features.caseoverviewdetails.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
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
import androidx.navigation.fragment.navArgs
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.databinding.FragmentCaseDetailsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaseDetailsFragment : Fragment(R.layout.fragment_case_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaseDetailsViewModel by viewModels { viewModelFactory }

    private val args by navArgs<CaseDetailsFragmentArgs>()

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
                CaseDetailsIntegration(viewModel)
            }
        }
    }

    @Composable
    fun CaseDetailsIntegration(viewModel: CaseDetailsViewModel) {

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        viewModel.handleAction(CaseDetailsViewAction.OnItemProvided(args.currentCase))

        CaseDetailsScreen(state, onAction = { action -> viewModel.handleAction(action) })
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (context.applicationContext as CaseWatcherApplication)
//            .getAppComponent()
//            .inject(this)
//    }
}
