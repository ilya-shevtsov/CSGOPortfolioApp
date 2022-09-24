package com.example.csgocaseswatcherapp.screens.portfolio.view.entities

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCasePortfolioBinding
import com.xwray.groupie.viewbinding.BindableItem

class PortfolioGroupieItem(
    private val portfolioCaseItem: PortfolioCaseItem
) : BindableItem<ItemCasePortfolioBinding>() {

    override fun getId(): Long {
        return portfolioCaseItem.caseName.hashCode().toLong()
    }

    override fun bind(viewBinding: ItemCasePortfolioBinding, position: Int) {
        viewBinding.caseImageView.setImageURI(portfolioCaseItem.caseImage)
        viewBinding.caseNameTextView.text = portfolioCaseItem.caseName
        viewBinding.caseAmount.text = viewBinding.root.context.getString(
            R.string.amount_of_cases, portfolioCaseItem.caseAmount.toString()
        )
        viewBinding.casePrice.text = viewBinding.root.context.getString(
            R.string.average_price, portfolioCaseItem.casePrice.toString()
        )
        viewBinding.caseOverallValue.text = viewBinding.root.context.getString(
            R.string.overall_value, portfolioCaseItem.caseOverallValue.toString()
        )
        viewBinding.caseProfitLoss.text = viewBinding.root.context.getString(
            R.string.profit_loss, portfolioCaseItem.caseProfitLoss.toString()
        )
    }

    override fun getLayout(): Int {
        return R.layout.item_case_portfolio
    }

    override fun initializeViewBinding(view: View): ItemCasePortfolioBinding {
        return ItemCasePortfolioBinding.bind(view)
    }
}