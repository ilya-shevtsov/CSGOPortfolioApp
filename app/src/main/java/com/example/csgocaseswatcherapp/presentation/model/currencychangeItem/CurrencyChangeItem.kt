package com.example.csgocaseswatcherapp.presentation.model.currencychangeItem

import android.view.View
import com.example.csgocaseswatcherapp.R
import com.example.csgocaseswatcherapp.databinding.ItemCurrencyChangeBinding
import com.xwray.groupie.viewbinding.BindableItem


class CurrencyChangeItem(
     val currencyName: String,

) : BindableItem<ItemCurrencyChangeBinding>() {

    override fun bind(viewBinding: ItemCurrencyChangeBinding, position: Int) {
        viewBinding.currencyName.text = currencyName
    }

    override fun getLayout(): Int {
        return R.layout.item_currency_change
    }

    override fun initializeViewBinding(view: View): ItemCurrencyChangeBinding {
        return ItemCurrencyChangeBinding.bind(view)
    }


}