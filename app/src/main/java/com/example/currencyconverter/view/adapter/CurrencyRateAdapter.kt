package com.example.currencyconverter.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.common.inflate
import com.example.currencyconverter.data.network.model.RateUI

class CurrencyRateAdapter(
    private val rates: MutableList<RateUI>,
    val onRateClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CurrencyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            parent.inflate(R.layout.currency_rate_item)
        )
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(rates[position], onRateClick)
    }

    fun updateRates(rates: List<RateUI>) {
        this.rates.clear()
        this.rates.addAll(rates)
        notifyDataSetChanged()
    }
}