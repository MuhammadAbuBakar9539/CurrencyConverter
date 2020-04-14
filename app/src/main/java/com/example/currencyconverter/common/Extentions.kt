package com.example.currencyconverter.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencyconverter.data.network.model.Currency
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun CircleImageView.loadImage(imgUrl: String) {
    Picasso.get().load(imgUrl).into(this)
}

fun String.getCurrency(conversionRate: Double = 1.0): Currency {
    return when (this) {
        "AUD" -> Currency(
            "AUD",
            "Australian Dollar",
            "https://www.countryflags.io/au/flat/64.png",
            conversionRate
        )

        "BGN" -> Currency(
            "BGN",
            "Bulgarian Lev",
            "https://www.countryflags.io/bg/flat/64.png",
            conversionRate
        )

        "BRL" -> Currency(
            "BRL",
            "Brazilian Real",
            "https://www.countryflags.io/br/flat/64.png",
            conversionRate
        )

        "CAD" -> Currency(
            "CAD",
            "Canadian Dollar",
            "https://www.countryflags.io/ca/flat/64.png",
            conversionRate
        )

        "CHF" -> Currency(
            "CHF",
            "Confoederatio Helvetica Franc",
            "https://www.countryflags.io/ch/flat/64.png",
            conversionRate
        )

        "CNY" -> Currency(
            "CNY",
            "Chinese Yuan",
            "https://www.countryflags.io/cn/flat/64.png",
            conversionRate
        )

        "CZK" -> Currency(
            "CZK",
            "Czech Koruna",
            "https://www.countryflags.io/cz/flat/64.png",
            conversionRate
        )

        "DKK" -> Currency(
            "DKK",
            "Denmark Krone",
            "https://www.countryflags.io/dk/flat/64.png",
            conversionRate
        )

        "EUR" -> Currency(
            "EUR",
            "Euro",
            "https://www.countryflags.io/eu/flat/64.png",
            conversionRate
        )

        "GBP" -> Currency(
            "GBP",
            "Great Britain Pound",
            "https://www.countryflags.io/gb/flat/64.png",
            conversionRate
        )

        "HKD" -> Currency(
            "HKD",
            "Hong Kong dollar",
            "https://www.countryflags.io/hk/flat/64.png",
            conversionRate
        )

        "HRK" -> Currency(
            "HRK",
            "Kuna",
            "https://www.countryflags.io/hr/flat/64.png",
            conversionRate
        )

        "HUF" -> Currency(
            "HUF",
            "Hungarian Forint",
            "https://www.countryflags.io/hu/flat/64.png",
            conversionRate
        )

        "IDR" -> Currency(
            "IDR",
            "Indonesian Rupiah",
            "https://www.countryflags.io/id/flat/64.png",
            conversionRate
        )

        "ILS" -> Currency(
            "ILS",
            "Israeli new shekel",
            "https://www.countryflags.io/il/flat/64.png",
            conversionRate
        )

        "INR" -> Currency(
            "INR",
            "Indian Rupee",
            "https://www.countryflags.io/in/flat/64.png",
            conversionRate
        )

        "ISK" -> Currency(
            "ISK",
            "Iceland Krona",
            "https://www.countryflags.io/is/flat/64.png",
            conversionRate
        )

        "JPY" -> Currency(
            "JPY",
            "Japanese Yen",
            "https://www.countryflags.io/jp/flat/64.png",
            conversionRate
        )

        "KRW" -> Currency(
            "KRW",
            "South Korean Won",
            "https://www.countryflags.io/kr/flat/64.png",
            conversionRate
        )

        "MXN" -> Currency(
            "MXN",
            "Mexican Peso",
            "https://www.countryflags.io/mx/flat/64.png",
            conversionRate
        )

        "MYR" -> Currency(
            "MYR",
            "Ringgit Malaysia",
            "https://www.countryflags.io/my/flat/64.png",
            conversionRate
        )

        "NOK" -> Currency(
            "NOK",
            "Norwegian Krone",
            "https://www.countryflags.io/no/flat/64.png",
            conversionRate
        )

        "NZD" -> Currency(
            "NZD",
            "New Zealand Dollar",
            "https://www.countryflags.io/nz/flat/64.png",
            conversionRate
        )

        "PHP" -> Currency(
            "PHP",
            "Philippine Peso",
            "https://www.countryflags.io/ph/flat/64.png",
            conversionRate
        )

        "PLN" -> Currency(
            "PLN",
            "Polish Zloty",
            "https://www.countryflags.io/pl/flat/64.png",
            conversionRate
        )

        "RON" -> Currency(
            "RON",
            "Romanian New Leu",
            "https://www.countryflags.io/ro/flat/64.png",
            conversionRate
        )

        "RUB" -> Currency(
            "RUB",
            "Russian Ruble",
            "https://www.countryflags.io/ru/flat/64.png",
            conversionRate
        )

        "SEK" -> Currency(
            "SEK",
            "Swedish Krona",
            "https://www.countryflags.io/se/flat/64.png",
            conversionRate
        )

        "SGD" -> Currency(
            "SGD",
            "Singapore Dollar",
            "https://www.countryflags.io/sg/flat/64.png",
            conversionRate
        )

        "THB" -> Currency(
            "THB",
            "Thailand Baht",
            "https://www.countryflags.io/th/flat/64.png",
            conversionRate
        )

        "USD" -> Currency(
            "USD",
            "US Dollar",
            "https://www.countryflags.io/us/flat/64.png",
            conversionRate
        )

        "ZAR" -> Currency(
            "ZAR",
            "South African Rand",
            "https://www.countryflags.io/za/flat/64.png",
            conversionRate
        )

        else -> Currency(
            this,
            "Wrong Currency",
            "https://www.countryflags.io/eu/flat/64.png",
            conversionRate
        )
    }
}