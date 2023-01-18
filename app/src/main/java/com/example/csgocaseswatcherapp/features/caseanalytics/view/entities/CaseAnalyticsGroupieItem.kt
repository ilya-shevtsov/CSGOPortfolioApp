package com.example.csgocaseswatcherapp.features.caseanalytics.view.entities

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCaseAnalyticsBinding
import com.xwray.groupie.viewbinding.BindableItem

data class CaseAnalyticsGroupieItem(
    val caseAnalyticsModel: CaseAnalyticsModel
) : BindableItem<ItemCaseAnalyticsBinding>() {
    override fun bind(viewBinding: ItemCaseAnalyticsBinding, position: Int) {
        viewBinding.caseImageView.setImageURI(caseAnalyticsModel.imageUrl)
        viewBinding.caseNameTextView.text = caseAnalyticsModel.caseName
        viewBinding.monthlyAvgReturnInPercent.text = viewBinding.root.context.getString(
            R.string.monthly_avg_return_in_percent_new,
            caseAnalyticsModel.monthlyAvgReturnInPercent.toString()
        )
        viewBinding.monthlyAvgReturnInRub.text = viewBinding.root.context.getString(
            R.string.monthly_avg_return_in_rub_new,
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
