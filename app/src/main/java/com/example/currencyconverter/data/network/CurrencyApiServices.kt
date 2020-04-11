package com.example.currencyconverter.data.network


import com.example.currencyconverter.common.ENDPOINT
import com.example.currencyconverter.data.network.model.CurrencyRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiServices {
    @GET(ENDPOINT)
    suspend fun getCurrencyRate(@Query("base") base: String): Response<CurrencyRateResponse>
}