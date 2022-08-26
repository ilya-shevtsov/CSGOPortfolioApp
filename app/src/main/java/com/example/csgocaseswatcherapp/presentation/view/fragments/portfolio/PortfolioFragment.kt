package com.example.csgocaseswatcherapp.presentation.view.fragments.portfolio

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.data.api.ApiTools
import com.example.csgocaseswatcherapp.databinding.FragmentPortfolioBinding
import com.example.csgocaseswatcherapp.presentation.model.AddCaseItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.ItemGroup
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItemMapper
import com.xwray.groupie.GroupieAdapter

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

            //PlaceHolder for cases in portfolio overview (later get from database)

            ItemCaseRecyclerView.adapter = caseListAdapter

            setFragmentResultListener("addedCase") { _, bundle ->

                val addedCase = bundle.getSerializable("addedCase") as AddCaseItem
                val portfolioItem = PortfolioItemMapper.map(addedCase)

                val portfolioItemGroup = ItemGroup(
                    listOf(portfolioItem)
                )
                caseListAdapter.add(portfolioItemGroup)
            }




            homeButton.setOnClickListener {
                findNavController().navigate(R.id.startFragment)
            }

            addCaseButton.setOnClickListener {
                findNavController().navigate(R.id.addCaseFragment)
            }
        }
    }

    private suspend fun getPreferredCurrency(): Int {
        val response = ApiTools.getApiService().getPreferredCurrency()
        val preferredCurrencyValue = response.preferredCurrency
        Log.e("ServerSide", "GetFromServer: $preferredCurrencyValue")
        return preferredCurrencyValue
    }
}


