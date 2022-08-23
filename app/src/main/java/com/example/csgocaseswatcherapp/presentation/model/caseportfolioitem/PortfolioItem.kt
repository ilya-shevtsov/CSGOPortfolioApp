package com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCasePortfolioBinding
import com.xwray.groupie.viewbinding.BindableItem


class PortfolioItem(
    private val caseImage: String,
    private val caseName: String,
    private val caseAmount: Int,
    private val casePrice: Double,
    private val caseOverallValue: Double,
    private val caseProfitLoss: Double
    ) : BindableItem<ItemCasePortfolioBinding>() {

    override fun bind(viewBinding: ItemCasePortfolioBinding, position: Int) {
        viewBinding.caseImage.setImageURI(caseImage)
        viewBinding.caseName.text = caseName
        viewBinding.caseAmount.text = caseAmount.toString()
        viewBinding.casePrice.text = casePrice.toString()
        viewBinding.caseOverallValue.text = caseOverallValue.toString()
        viewBinding.caseProfitLoss.text = caseProfitLoss.toString()
    }

    override fun getLayout(): Int {
        return R.layout.item_case_portfolio
    }

    override fun initializeViewBinding(view: View): ItemCasePortfolioBinding {
        return ItemCasePortfolioBinding.bind(view)
    }


}