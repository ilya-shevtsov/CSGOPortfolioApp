package com.example.csgocaseswatcherapp.presentation.model.caseportfolioitem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCasePortfolioBinding
import com.xwray.groupie.viewbinding.BindableItem


class PortfolioGroupieItem(
    val caseImage: String,
    val caseName: String,
    val caseAmount: Int,
    val casePrice: Double,
    val caseOverallValue: Double,
    val caseProfitLoss: Double
) : BindableItem<ItemCasePortfolioBinding>() {

    override fun getId(): Long {
        return caseName.hashCode().toLong()
    }

//    iewBinding.root.context.getString(
//    R.string.case_lowest_price,
//    caseOverviewModel.lowestPrice.toString()
//    )

    override fun bind(viewBinding: ItemCasePortfolioBinding, position: Int) {
        viewBinding.caseImageView.setImageURI(caseImage)
        viewBinding.caseNameTextView.text = caseName
        viewBinding.caseAmount.text = viewBinding.root.context.getString(
            R.string.amount_of_cases, caseAmount.toString()
        )
        viewBinding.casePrice.text = viewBinding.root.context.getString(
            R.string.average_price, casePrice.toString()
        )
        viewBinding.caseOverallValue.text = viewBinding.root.context.getString(
            R.string.overall_value, caseOverallValue.toString()
        )
        viewBinding.caseProfitLoss.text = viewBinding.root.context.getString(
            R.string.profit_loss, caseProfitLoss.toString()
        )
    }

    override fun getLayout(): Int {
        return R.layout.item_case_portfolio
    }

    override fun initializeViewBinding(view: View): ItemCasePortfolioBinding {
        return ItemCasePortfolioBinding.bind(view)
    }


}