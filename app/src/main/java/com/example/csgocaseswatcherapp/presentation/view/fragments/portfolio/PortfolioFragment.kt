package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemGroup
import com.xwray.groupie.GroupieAdapter

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    private lateinit var binding: FragmentPortfolioBinding
    private val caseListAdapter = GroupieAdapter()

    override fun onResume() {
        super.onResume()
        val currencies = resources.getStringArray(R.array.currency_array)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,currencies)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)

        binding.ItemCaseRecyclerView.adapter = caseListAdapter

        val portfolioItemGroup = PortfolioItemGroup(
            listOf(
                PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                ), PortfolioItem(
                    caseImage = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
                    caseName = "Operation Breakout Weapon Case",
                    caseAmount = 25,
                    casePrice = 85.3,
                    caseOverallValue = 2132.5,
                    caseProfitLoss = 500.2
                )
            )
        )
        caseListAdapter.add(portfolioItemGroup)

        val currencies = resources.getStringArray(R.array.currency_array)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,currencies)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

//        binding.currencyChangeButtonUSD.setOnClickListener {
//            val prefCur = PreferredCurrencyDto(1)
//            CoroutineScope(Dispatchers.IO).launch {
//                getApiService().postPreferredCurrency(prefCur)
//                Log.e("M_PortfolioFragment.post", "USD")
//            }
//
//        }
//        binding.currencyChangeButtonRUB.setOnClickListener {
//            val prefCur = PreferredCurrencyDto(5)
//            CoroutineScope(Dispatchers.IO).launch {
//                getApiService().postPreferredCurrency(prefCur)
//                Log.e("M_PortfolioFragment.post", "RUB")
//            }
//
//        }

        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.startFragment)
        }

        binding.addCaseButton.setOnClickListener {
            findNavController().navigate(R.id.addCaseFragment)
        }


        return binding.root
    }
}