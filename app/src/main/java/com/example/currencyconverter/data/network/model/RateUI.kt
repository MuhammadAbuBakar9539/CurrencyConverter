package com.example.currencyconverter.data.network.model

data class RateUI(
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    var conversionRate: Double
)