package com.example.currencyconverter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CurrencyRateViewModelFactory @Inject constructor(
    private val repository: CurrencyRateRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyRateViewModel(repository, coroutineContextProvider) as T
    }
}