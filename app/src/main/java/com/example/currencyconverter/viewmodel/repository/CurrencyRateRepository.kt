package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.model.CurrencyRateModel
import retrofit2.Response

interface CurrencyRateRepository {
    suspend fun getCurrencyRate(base: String): Response<CurrencyRateModel>
}