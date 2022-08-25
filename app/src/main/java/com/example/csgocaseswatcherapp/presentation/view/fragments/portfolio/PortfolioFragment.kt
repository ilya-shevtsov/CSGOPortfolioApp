package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.data.api.ApiTools.Companion.getApiService
import com.example.csgocaseswatcherapp.data.model.prederredcurrencydto.PreferredCurrencyDto
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.ItemGroup
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    private lateinit var binding: FragmentPortfolioBinding
    private val caseListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            val portfolioItemGroup = ItemGroup(listOf(
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
                ))

            ItemCaseRecyclerView.adapter = caseListAdapter
            caseListAdapter.add(portfolioItemGroup)

            setFragmentResultListener("preferredCurrency") { _, bundle ->

                val preferredCurrency = bundle.getString("bundleKey")

                currencyChangeButton.text = preferredCurrency

                when (preferredCurrency) {
                    "USD" -> { sendPreferredCurrency(PreferredCurrencyDto(1)) }
                    "RUB" -> { sendPreferredCurrency(PreferredCurrencyDto(5)) }
                }
            }

            currencyChangeButton.setOnClickListener {
                findNavController().navigate(R.id.currencyChangeFragment)
            }

            homeButton.setOnClickListener {
                findNavController().navigate(R.id.startFragment)
            }

            addCaseButton.setOnClickListener {
                findNavController().navigate(R.id.addCaseFragment)
            }
        }
    }

    private fun sendPreferredCurrency(preferredCurrency: PreferredCurrencyDto){
        CoroutineScope(Dispatchers.IO).launch {
            getApiService().postPreferredCurrency(preferredCurrency)
        }
    }

}
