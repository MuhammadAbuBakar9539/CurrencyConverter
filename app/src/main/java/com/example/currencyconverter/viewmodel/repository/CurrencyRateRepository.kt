package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.data.network.model.CurrencyRateResponse
import com.example.currencyconverter.data.network.model.RateUI
import retrofit2.Response

interface CurrencyRateRepository {
    suspend fun getCurrencyRate(base: String): Pair<List<RateUI>?, String?>
}