package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.data.network.CurrencyApiServices
import com.example.currencyconverter.data.network.model.CurrencyRateResponse
import com.example.currencyconverter.data.network.model.RateUI
import retrofit2.Response
import javax.inject.Inject

class CurrencyRateRepositoryImpl @Inject constructor(private val currencyApiServices: CurrencyApiServices) :
    CurrencyRateRepository {
    override suspend fun getCurrencyRate(base: String): Pair<List<RateUI>?, String?> {
        val response = currencyApiServices.getCurrencyRate(base)
        return if (response.isSuccessful) {
            Pair(response.body()?.getRateUIs() ?: emptyList(), null)
        } else {
            Pair(null, response.message())
        }
    }
}