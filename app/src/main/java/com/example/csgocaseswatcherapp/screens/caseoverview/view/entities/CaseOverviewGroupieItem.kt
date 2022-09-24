package com.example.csgocaseswatcherapp.screens.caseoverview.view.entities

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCaseBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CaseOverviewGroupieItem(
    val caseOverviewModel: CaseOverviewModel
) : BindableItem<ItemCaseBinding>() {
    override fun bind(viewBinding: ItemCaseBinding, position: Int) {
        viewBinding.caseImageView.setImageURI(caseOverviewModel.imageUrl)
        viewBinding.caseNameTextView.text = caseOverviewModel.caseName
        viewBinding.lowestPriceTextView.text =
            viewBinding.root.context.getString(
                R.string.case_lowest_price,
                caseOverviewModel.lowestPrice.toString()
            )
        viewBinding.volumeTextView.text =
            viewBinding.root.context.getString(R.string.case_volume, caseOverviewModel.volume.toString())
        viewBinding.medianPriceTextView.text =
            viewBinding.root.context.getString(
                R.string.case_median_price,
                caseOverviewModel.medianPrice.toString()
            )
    }

    override fun getLayout(): Int {
        return R.layout.item_case
    }

    override fun initializeViewBinding(view: View): ItemCaseBinding {
        return ItemCaseBinding.bind(view)
    }
}