package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.data.network.model.Currency

interface CurrencyRateRepository {
    suspend fun getCurrencyRate(base: String): Pair<List<Currency>?, String?>
}