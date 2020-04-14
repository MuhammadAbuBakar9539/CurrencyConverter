package com.example.currencyconverter.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencyconverter.data.network.model.Currency

class CurrencyDiffCallback(private val oldList: List<Currency>, private val newList: List<Currency>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].shortName == newList[newItemPosition].shortName
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}