package com.example.csgocaseswatcherapp.features.currencychange.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.core.CaseWatcherApplication
import com.example.csgocaseswatcherapp.databinding.FragmentCurrencyChangeBinding
import com.example.csgocaseswatcherapp.features.start.view.entities.CurrencyChangeItem
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyChangeFragment : Fragment(R.layout.fragment_currency_change) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyChangeViewModel by viewModels {viewModelFactory}

    private lateinit var binding: FragmentCurrencyChangeBinding

    private val currencyListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            currencyChangeRecyclerView.adapter = currencyListAdapter

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiState.collect { uiState ->
                        val currencyChangeItemList = uiState.currencyList.map { item ->
                            CurrencyChangeItem(item)
                        }
                        currencyListAdapter.update(currencyChangeItemList)
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

            currencyListAdapter.setOnItemClickListener { item, _ ->
                when (item) {
                    is CurrencyChangeItem -> {
                        viewModel.handleAction(CurrencyChangeViewAction.OnCurrencyClicked(item.currencyName))
                    }
                }
            }
        }
    }

    private fun handleEvent(uiEvent: CurrencyChangeViewEvent) {
        when (uiEvent) {
            is CurrencyChangeViewEvent.NavigateToStartWithPreferredCurrency -> {
                navigateToStartWithPreferredCurrency(uiEvent)
            }
        }

    }

    private fun navigateToStartWithPreferredCurrency(
        uiEvent: CurrencyChangeViewEvent.NavigateToStartWithPreferredCurrency
    ) {
        setFragmentResult(
            "preferredCurrency",
            bundleOf("preferredCurrency" to uiEvent.currencyName)
        )
        findNavController().popBackStack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CaseWatcherApplication)
            .getAppComponent()
            .inject(this)
    }
}