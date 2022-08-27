package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentCurrencyChangeBinding
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.ItemGroup
import com.example.csgocaseswatcherapp.presentation.model.currencyChangeItem.CurrencyChangeItem
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch

class CurrencyChangeFragment : Fragment(R.layout.fragment_currency_change) {

    private val viewModel: CurrencyChangeViewModel by viewModels()


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

                        //PlaceHolder for currencies (later get from database)

                        val currencyChangeGroup = uiState.currencyList.map { item ->
                            CurrencyChangeItem(item)
                        }
                        currencyListAdapter.update(currencyChangeGroup)
                    }
                }
            }

            currencyListAdapter.setOnItemClickListener { item, _ ->
                when (item) {
                    is CurrencyChangeItem -> {
                        val preferredCurrency = item.currencyName
                        setFragmentResult(
                            "preferredCurrency",
                            bundleOf("bundleKey" to preferredCurrency)
                        )
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}