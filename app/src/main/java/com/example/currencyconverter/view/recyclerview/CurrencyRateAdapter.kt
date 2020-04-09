package com.example.currencyconverter.view.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.common.inflate
import com.example.currencyconverter.model.CurrencyRateModel

class CurrencyRateAdapter(
    private val currencyRate: CurrencyRateModel,
    private val rateUi: RateUI
) :
    RecyclerView.Adapter<CurrencyRateAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            parent.inflate(R.layout.currency_rate_item)
        )
    }

    override fun getItemCount(): Int {
        return rateUi.shortNameList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}