package com.example.csgocaseswatcherapp.presentation.view.fragments.addCaseFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.FragmentAddCaseBinding
import com.example.csgocaseswatcherapp.presentation.model.AddCaseItem
import com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem.PortfolioItem

class AddCaseFragment : Fragment(R.layout.fragment_add_case) {

    private lateinit var binding: FragmentAddCaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCaseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {

            val caseNameArray = resources.getStringArray(R.array.case_name_array)

            val caseNameArrayAdapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    caseNameArray
                )

            addCaseCaseName.threshold = 1

            addCaseCaseName.setAdapter(caseNameArrayAdapter)

            addCaseButton.setOnClickListener {

                val addedCase = AddCaseItem(
                    name = addCaseCaseName.toString(),
                    amount = addCaseCaseAmount.text.toString().toInt(),
                    purchasePrice = addCaseCasePurchasePrice.text.toString().toDouble()
                )
                Log.e("getOverAllValue","This is the addedCase price: ${addedCase.purchasePrice}")

                setFragmentResult(
                    "addedCase",
                    bundleOf("addedCase" to addedCase)
                )
                findNavController().popBackStack()
            }
        }
    }
}