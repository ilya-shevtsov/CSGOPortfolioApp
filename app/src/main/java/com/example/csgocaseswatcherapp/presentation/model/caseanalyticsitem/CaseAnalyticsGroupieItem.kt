package com.example.csgocaseswatcherapp.presentation.model.caseanalyticsitem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCaseAnalyticsBinding
import com.example.csgocaseswatcherapp.databinding.ItemCaseBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CaseAnalyticsGroupieItem(
    val caseAnalyticsModel: CaseAnalyticsModel
) : BindableItem<ItemCaseAnalyticsBinding>() {
    override fun bind(viewBinding: ItemCaseAnalyticsBinding, position: Int) {
//        viewBinding.caseImageView.setImageURI("https://i5.walmartimages.com/asr/3d09dd36-7035-4b9f-b965-d4a9a36e5c7b_1.bc4a5060a274c0c2db4970d9075daa90.jpeg?odnWidth=612&odnHeight=612&odnBg=ffffff")
        viewBinding.caseImageView.setImageURI(caseAnalyticsModel.imageUrl)
        viewBinding.caseNameTextView.text = caseAnalyticsModel.caseName
        viewBinding.monthlyAvgReturnInPercent.text = viewBinding.root.context.getString(
            R.string.monthly_avg_return_in_percent,
            caseAnalyticsModel.monthlyAvgReturnInPercent.toString()
        )
        viewBinding.monthlyAvgReturnInRub.text = viewBinding.root.context.getString(
            R.string.monthly_avg_return_in_rub,
            caseAnalyticsModel.monthlyAvgReturnInRUB.toString()
        )
    }

    override fun getLayout(): Int {
        return R.layout.item_case_analytics
    }

    override fun initializeViewBinding(view: View): ItemCaseAnalyticsBinding {
        return ItemCaseAnalyticsBinding.bind(view)
    }

}