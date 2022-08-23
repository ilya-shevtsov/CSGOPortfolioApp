package com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCasePortfolioHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class PortfolioItemHeader(
    private val caseNameHeader: String,
    private val caseAmountHeader: String,
    private val casePriceHeader: String,
    private val caseOverallValueHeader: String,
    private val caseProfitLossHeader: String
) : BindableItem<ItemCasePortfolioHeaderBinding>() {

    override fun bind(viewBinding: ItemCasePortfolioHeaderBinding, position: Int) {
        viewBinding.caseNameHeader.text = caseNameHeader
        viewBinding.caseAmountHeader.text = caseAmountHeader
        viewBinding.casePriceHeader.text = casePriceHeader
        viewBinding.caseOverallValueHeader.text = caseOverallValueHeader
        viewBinding.caseProfitLossHeader.text = caseProfitLossHeader
    }

    override fun getLayout(): Int {
        return R.layout.item_case_portfolio_header
    }

    override fun initializeViewBinding(view: View): ItemCasePortfolioHeaderBinding {
        return ItemCasePortfolioHeaderBinding.bind(view)
    }
}