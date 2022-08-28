package com.example.csgocaseswatcherapp.presentation.model.caseoverviewitem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCaseBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CaseOverViewGroupieItem(
    val caseOverviewItem: CaseOverviewItem
) : BindableItem<ItemCaseBinding>() {
    override fun bind(viewBinding: ItemCaseBinding, position: Int) {
        viewBinding.caseImage.setImageURI(caseOverviewItem.imageUrl)
        viewBinding.caseName.text = caseOverviewItem.caseName
        viewBinding.lowestPrice.text =
            viewBinding.root.context.getString(
                R.string.case_lowest_price,
                caseOverviewItem.lowestPrice.toString()
            )
        viewBinding.volume.text =
            viewBinding.root.context.getString(R.string.case_volume, caseOverviewItem.volume.toString())
        viewBinding.medianPrice.text =
            viewBinding.root.context.getString(
                R.string.case_median_price,
                caseOverviewItem.medianPrice.toString()
            )
    }

    override fun getLayout(): Int {
        return R.layout.item_case
    }

    override fun initializeViewBinding(view: View): ItemCaseBinding {
        return ItemCaseBinding.bind(view)
    }

}