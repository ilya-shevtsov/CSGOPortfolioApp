package com.example.csgocaseswatcherapp.features.currencychange.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
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
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.databinding.FragmentCurrencyChangeBinding
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyChangeFragment : Fragment(R.layout.fragment_currency_change) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyChangeViewModel by viewModels {viewModelFactory}

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

        composeView.setContent {
            AppTheme {
                CurrencyChangeIntegration(viewModel)
            }
        }
        composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }


    private fun navigateToStartWithPreferredCurrency(

    ) {
//        setFragmentResult(
//            "preferredCurrency",
//            bundleOf("preferredCurrency" to uiEvent.currencyName)
//        )
        findNavController().popBackStack()
    }

    @Composable
    fun CurrencyChangeIntegration(viewModel:CurrencyChangeViewModel){

        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is CurrencyChangeViewEvent.NavigateToStartWithPreferredCurrency -> navigateToStartWithPreferredCurrency()
                }
            }
        }

        CurrencyChangeScreen(
            state = state,
            onCurrencyClicked = {clicked -> viewModel.handleAction(CurrencyChangeViewAction.OnCurrencyClicked(clicked))}
        )
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (context.applicationContext as CaseWatcherApplication)
//            .getAppComponent()
//            .inject(this)
//    }
}