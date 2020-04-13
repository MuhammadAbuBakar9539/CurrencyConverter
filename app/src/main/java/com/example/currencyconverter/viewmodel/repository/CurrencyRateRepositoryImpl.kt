package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.data.network.CurrencyApiServices
import com.example.currencyconverter.data.network.model.Currency
import javax.inject.Inject

class CurrencyRateRepositoryImpl @Inject constructor(private val currencyApiServices: CurrencyApiServices) :
    CurrencyRateRepository {
    override suspend fun getCurrencyRate(base: String): Pair<List<Currency>?, String?> {
        val response = currencyApiServices.getCurrencyRate(base)
        return if (response.isSuccessful) {
            Pair(response.body()?.getCurrencies() ?: emptyList(), null)
        } else {
            Pair(null, response.message())
        }
    }
}