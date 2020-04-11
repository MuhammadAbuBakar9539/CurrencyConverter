package com.example.currencyconverter.view.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.common.loadImage
import com.example.currencyconverter.data.network.model.RateUI
import kotlinx.android.synthetic.main.currency_rate_item.view.*

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(rateUI: RateUI, onAmountChanged: (String) -> Unit, onRateClick: (Int, String) -> Unit) {
        itemView.tv_currency_short.text = rateUI.shortName
        itemView.tv_currency_full.text = rateUI.fullName
        itemView.cimg_currency_flag.loadImage(rateUI.imageUrl)
        if (adapterPosition == 0) {
            itemView.et_currency_rate.setText(String.format("%.2f", rateUI.conversionRate))
            itemView.et_currency_rate.visibility = View.VISIBLE
            itemView.tv_currency_rate.visibility = View.GONE
            itemView.setOnClickListener(null)
            itemView.et_currency_rate.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    onAmountChanged.invoke(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        } else {
            itemView.et_currency_rate.visibility = View.GONE
            itemView.tv_currency_rate.visibility = View.VISIBLE
            itemView.tv_currency_rate.text = String.format("%.2f", rateUI.conversionRate)
            itemView.setOnClickListener {
                onRateClick.invoke(adapterPosition, rateUI.shortName)
            }
        }

    }
}