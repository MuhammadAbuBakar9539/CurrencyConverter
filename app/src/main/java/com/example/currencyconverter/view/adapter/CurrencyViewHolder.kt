package com.example.currencyconverter.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.common.loadImage
import com.example.currencyconverter.data.network.model.Currency
import kotlinx.android.synthetic.main.currency_rate_item.view.*

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        currency: Currency,
        onRateClick: (Currency) -> Unit
    ) {
        itemView.tv_currency_short.text = currency.shortName
        itemView.tv_currency_full.text = currency.fullName
        itemView.cimg_currency_flag.loadImage(currency.imageUrl)
        itemView.tv_currency_rate.text = String.format("%.2f", currency.conversionRate)
        itemView.setOnClickListener {
            onRateClick.invoke(currency)
        }
    }
}