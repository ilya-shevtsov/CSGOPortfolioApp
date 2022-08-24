package com.example.csgocaseswatcherapp.presentation.view.fragments.currencychange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentCurrencyChangeBinding
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.ItemGroup
import com.example.csgocaseswatcherapp.presentation.model.currencyChangeItem.CurrencyChangeItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter

class CurrencyChangeFragment : Fragment(R.layout.fragment_currency_change) {

    private lateinit var binding: FragmentCurrencyChangeBinding
    private val currencyListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyChangeBinding.inflate(inflater, container, false)
        binding.currencyChangeRecyclerView.adapter = currencyListAdapter

        val currencyChangeGroup = ItemGroup(
            listOf(
                CurrencyChangeItem("USD"),
                CurrencyChangeItem("RUB")
            )
        )
        currencyListAdapter.add(currencyChangeGroup)

        currencyListAdapter.setOnItemClickListener { item, _ ->
            when(item){
                is CurrencyChangeItem -> {
                    val preferredCurrency = item.currencyName
                    setFragmentResult("preferredCurrency",bundleOf("bundleKey" to preferredCurrency))
                    findNavController().popBackStack()
                }
            }

        }

        return binding.root
    }
}