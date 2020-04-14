package com.example.currencyconverter.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.common.inflate
import com.example.currencyconverter.data.network.model.Currency

class CurrencyRateAdapter(
    private var currency: List<Currency>,
    private val onRateClick: (Currency) -> Unit
) :
    RecyclerView.Adapter<CurrencyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            parent.inflate(R.layout.currency_rate_item)
        )
    }

    override fun getItemCount(): Int {
        return currency.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currency[position], onRateClick)
    }

    fun updateRates(rates: List<Currency>) {
        val oldList = currency
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(oldList, rates))
        diffResult.dispatchUpdatesTo(this)
        this.currency = rates

    }
}