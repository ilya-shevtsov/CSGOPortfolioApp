package com.example.csgocaseswatcherapp.features.portfolio.view.entities

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCasePortfolioBinding
import com.xwray.groupie.viewbinding.BindableItem

class PortfolioGroupieItem(
    private val portfolioItem: PortfolioItem
) : BindableItem<ItemCasePortfolioBinding>() {

    override fun getId(): Long {
        return portfolioItem.caseName.hashCode().toLong()
    }

    override fun bind(viewBinding: ItemCasePortfolioBinding, position: Int) {
        viewBinding.caseImageView.setImageURI(portfolioItem.caseImage)
        viewBinding.caseNameTextView.text = portfolioItem.caseName
        viewBinding.caseAmount.text = viewBinding.root.context.getString(
            R.string.amount_of_cases, portfolioItem.caseAmount.toString()
        )
        viewBinding.casePrice.text = viewBinding.root.context.getString(
            R.string.average_price, portfolioItem.casePrice.toString()
        )
        viewBinding.caseOverallValue.text = viewBinding.root.context.getString(
            R.string.overall_value, portfolioItem.caseOverallValue.toString()
        )
        viewBinding.caseProfitLoss.text = viewBinding.root.context.getString(
            R.string.profit_loss, portfolioItem.caseProfitLoss.toString()
        )
    }

    override fun getLayout(): Int {
        return R.layout.item_case_portfolio
    }

    override fun initializeViewBinding(view: View): ItemCasePortfolioBinding {
        return ItemCasePortfolioBinding.bind(view)
    }
}
