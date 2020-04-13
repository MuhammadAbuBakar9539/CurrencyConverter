package com.example.currencyconverter.data.network.model

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("AUD")
    val aud: Double = 1.0,
    @SerializedName("BGN")
    val bgn: Double = 1.0,
    @SerializedName("BRL")
    val brl: Double = 1.0,
    @SerializedName("CAD")
    val cad: Double = 1.0,
    @SerializedName("CHF")
    val chf: Double = 1.0,
    @SerializedName("CNY")
    val cny: Double = 1.0,
    @SerializedName("CZK")
    val czk: Double = 1.0,
    @SerializedName("EUR")
    val eur: Double = 1.0,
    @SerializedName("DKK")
    val dkk: Double = 1.0,
    @SerializedName("GBP")
    val gbp: Double = 1.0,
    @SerializedName("HKD")
    val hkd: Double = 1.0,
    @SerializedName("HRK")
    val hrk: Double = 1.0,
    @SerializedName("HUF")
    val huf: Double = 1.0,
    @SerializedName("IDR")
    val idr: Double = 1.0,
    @SerializedName("ILS")
    val ils: Double = 1.0,
    @SerializedName("INR")
    val inr: Double = 1.0,
    @SerializedName("ISK")
    val isk: Double = 1.0,
    @SerializedName("JPY")
    val jpy: Double = 1.0,
    @SerializedName("KRW")
    val krw: Double = 1.0,
    @SerializedName("MXN")
    val mxn: Double = 1.0,
    @SerializedName("MYR")
    val myr: Double = 1.0,
    @SerializedName("NOK")
    val nok: Double = 1.0,
    @SerializedName("NZD")
    val nzn: Double = 1.0,
    @SerializedName("PHP")
    val php: Double = 1.0,
    @SerializedName("PLN")
    val pln: Double = 1.0,
    @SerializedName("RON")
    val ron: Double = 1.0,
    @SerializedName("RUB")
    val rub: Double = 1.0,
    @SerializedName("SEK")
    val sek: Double = 1.0,
    @SerializedName("SGD")
    val sgd: Double = 1.0,
    @SerializedName("THB")
    val thb: Double = 1.0,
    @SerializedName("USD")
    val usd: Double = 1.0,
    @SerializedName("ZAR")
    val zar: Double = 1.0
)