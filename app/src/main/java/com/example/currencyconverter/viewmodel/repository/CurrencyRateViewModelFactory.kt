package com.example.currencyconverter.viewmodel.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CurrencyRateViewModelFactory @Inject constructor(private val repository: CurrencyRateRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyRateViewModel(repository) as T
    }
}