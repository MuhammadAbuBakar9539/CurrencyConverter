package com.example.currencyconverter.viewmodel.repository

import com.example.currencyconverter.common.network.Client
import com.example.currencyconverter.model.CurrencyRateModel
import retrofit2.Response
import javax.inject.Inject

class CurrencyRateRepositoryImpl @Inject constructor(private val client: Client):CurrencyRateRepository {
    override suspend fun getCurrencyRate(base: String): Response<CurrencyRateModel> {
        return client.getCurrencyRate(base)
    }
}