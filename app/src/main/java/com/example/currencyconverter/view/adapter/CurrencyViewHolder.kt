package com.example.currencyconverter.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.common.loadImage
import com.example.currencyconverter.data.network.model.RateUI
import kotlinx.android.synthetic.main.currency_rate_item.view.*

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(rateUI: RateUI, onRateClick: (Int) -> Unit) {
        itemView.tv_currency_short.text = rateUI.shortName
        itemView.tv_currency_full.text = rateUI.fullName
        itemView.et_currency_rate.setText(rateUI.conversionRate.toString())
        itemView.cimg_currency_flag.loadImage(rateUI.imageUrl)
        itemView.setOnClickListener {
            onRateClick.invoke(adapterPosition)
        }
    }

}