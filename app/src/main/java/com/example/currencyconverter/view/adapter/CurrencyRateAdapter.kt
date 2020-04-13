package com.example.currencyconverter.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.common.inflate
import com.example.currencyconverter.data.network.model.Currency

class CurrencyRateAdapter(
    private var currency: MutableList<Currency>,
    private val onAmountChanged: (String) -> Unit,
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
        holder.bind(currency[position], onAmountChanged, onRateClick, position)
    }

    fun updateRates(rates: List<Currency>) {
        val oldList = mutableListOf<Currency>().apply {
            this.addAll(this@CurrencyRateAdapter.currency)
        }
        this.currency = mutableListOf<Currency>().apply {
            addAll(rates)
        }
        this.currency.forEachIndexed { index, currency ->
            if (oldList.size <= index) {
                notifyItemInserted(index)
            } else if (oldList[index] != this.currency[index]) {
                notifyItemChanged(index)
            }
        }
    }
}