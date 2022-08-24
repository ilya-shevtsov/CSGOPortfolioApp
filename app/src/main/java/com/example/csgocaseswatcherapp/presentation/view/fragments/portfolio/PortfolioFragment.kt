package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.ItemGroup
import com.xwray.groupie.GroupieAdapter

class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {

    private lateinit var binding: FragmentPortfolioBinding
    private val caseListAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)

        binding.ItemCaseRecyclerView.adapter = caseListAdapter

        val portfolioItemGroup = ItemGroup(
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


//        binding.currencyChangeButtonUSD.setOnClickListener {
//        CoroutineScope(Dispatchers.IO).launch {
//            getApiService().postPreferredCurrency(PreferredCurrencyDto(1))
//            Log.e("M_PortfolioFragment.post", "USD")
//        }
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

        binding.currencyChangeButton.setOnClickListener {
            setFragmentResultListener("preferredCurrency") {
                    _, bundle ->
                val preferredCurrency = bundle.getString("bundleKey")
                binding.currencyChangeButton.text = preferredCurrency
            }
            findNavController().navigate(R.id.currencyChangeFragment)
        }


        return binding.root
    }
}