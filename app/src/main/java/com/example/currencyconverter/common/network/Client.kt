package com.example.currencyconverter.common.network


import com.example.currencyconverter.common.ENDPOINT
import com.example.currencyconverter.model.CurrencyRateModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {
    @GET(ENDPOINT)
    suspend fun getCurrencyRate(@Query("base") base: String): Response<CurrencyRateModel>
}