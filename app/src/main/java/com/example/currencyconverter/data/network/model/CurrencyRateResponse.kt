package com.example.currencyconverter.data.network.model


data class CurrencyRateResponse(
    val baseCurrency: String,
    val rates: Rates
) {
    fun getRateUIs(): List<RateUI> {
        val rateUIs = ArrayList<RateUI>()
        rateUIs.add(
            RateUI(
                "AUD",
                "Australian Dollar",
                "https://www.countryflags.io/au/flat/64.png",
                rates.aud
            )
        )
        rateUIs.add(
            RateUI(
                "BGN",
                "Bulgarian Lev",
                "https://www.countryflags.io/bg/flat/64.png",
                rates.bgn
            )
        )
        rateUIs.add(
            RateUI(
                "BRL",
                "Brazilian Real",
                "https://www.countryflags.io/br/flat/64.png",
                rates.brl
            )
        )
        rateUIs.add(
            RateUI(
                "CAD",
                "Canadian Dollar",
                "https://www.countryflags.io/ca/flat/64.png",
                rates.cad
            )
        )
        rateUIs.add(
            RateUI(
                "CHF",
                "Confoederatio Helvetica Franc",
                "https://www.countryflags.io/ch/flat/64.png",
                rates.chf
            )
        )
        rateUIs.add(
            RateUI(
                "CNY",
                "Chinese Yuan",
                "https://www.countryflags.io/cn/flat/64.png",
                rates.cny
            )
        )
        rateUIs.add(
            RateUI(
                "CZK",
                "Czech Koruna",
                "https://www.countryflags.io/cz/flat/64.png",
                rates.czk
            )
        )
        rateUIs.add(
            RateUI(
                "DKK",
                "Denmark Krone",
                "https://www.countryflags.io/dk/flat/64.png",
                rates.dkk
            )
        )
        rateUIs.add(
            RateUI(
                "GBP",
                "Great Britain Pound",
                "https://www.countryflags.io/gb/flat/64.png",
                rates.gbp
            )
        )
        rateUIs.add(
            RateUI(
                "HKD",
                "Hong Kong dollar",
                "https://www.countryflags.io/hk/flat/64.png",
                rates.hkd
            )
        )
        rateUIs.add(RateUI("HRK", "Kuna", "https://www.countryflags.io/hr/flat/64.png", rates.hrk))
        rateUIs.add(
            RateUI(
                "HUF",
                "Hungarian Forint",
                "https://www.countryflags.io/hu/flat/64.png",
                rates.huf
            )
        )
        rateUIs.add(
            RateUI(
                "IDR",
                "Indonesian Rupiah",
                "https://www.countryflags.io/id/flat/64.png",
                rates.idr
            )
        )
        rateUIs.add(
            RateUI(
                "ILS",
                "Israeli new shekel",
                "https://www.countryflags.io/il/flat/64.png",
                rates.ils
            )
        )
        rateUIs.add(
            RateUI(
                "INR",
                "Indian Rupee",
                "https://www.countryflags.io/in/flat/64.png",
                rates.inr
            )
        )
        rateUIs.add(
            RateUI(
                "ISK",
                "Iceland Krona",
                "https://www.countryflags.io/is/flat/64.png",
                rates.isk
            )
        )
        rateUIs.add(
            RateUI(
                "JPY",
                "Japanese Yen",
                "https://www.countryflags.io/jp/flat/64.png",
                rates.jpy
            )
        )
        rateUIs.add(
            RateUI(
                "KRW",
                "South Korean Won",
                "https://www.countryflags.io/kr/flat/64.png",
                rates.krw
            )
        )
        rateUIs.add(
            RateUI(
                "MXN",
                "Mexican Peso",
                "https://www.countryflags.io/mx/flat/64.png",
                rates.mxn
            )
        )
        rateUIs.add(
            RateUI(
                "MYR",
                "Ringgit Malaysia",
                "https://www.countryflags.io/my/flat/64.png",
                rates.myr
            )
        )
        rateUIs.add(
            RateUI(
                "NOK",
                "Norwegian Krone",
                "https://www.countryflags.io/no/flat/64.png",
                rates.nok
            )
        )
        rateUIs.add(
            RateUI(
                "NZD",
                "New Zealand Dollar",
                "https://www.countryflags.io/nz/flat/64.png",
                rates.nzn
            )
        )
        rateUIs.add(
            RateUI(
                "PHP",
                "Philippine Peso",
                "https://www.countryflags.io/ph/flat/64.png",
                rates.php
            )
        )
        rateUIs.add(
            RateUI(
                "PLN",
                "Polish Zloty",
                "https://www.countryflags.io/pl/flat/64.png",
                rates.pln
            )
        )
        rateUIs.add(
            RateUI(
                "RON",
                "Romanian New Leu",
                "https://www.countryflags.io/ro/flat/64.png",
                rates.ron
            )
        )
        rateUIs.add(
            RateUI(
                "RUB",
                "Russian Ruble",
                "https://www.countryflags.io/ru/flat/64.png",
                rates.rub
            )
        )
        rateUIs.add(
            RateUI(
                "SEK",
                "Swedish Krona",
                "https://www.countryflags.io/se/flat/64.png",
                rates.sek
            )
        )
        rateUIs.add(
            RateUI(
                "SGD",
                "Singapore Dollar",
                "https://www.countryflags.io/sg/flat/64.png",
                rates.sgd
            )
        )
        rateUIs.add(
            RateUI(
                "THB",
                "Thailand Baht",
                "https://www.countryflags.io/th/flat/64.png",
                rates.thb
            )
        )
        rateUIs.add(
            RateUI(
                "USD",
                "United States Dollar",
                "https://www.countryflags.io/us/flat/64.png",
                rates.usd
            )
        )
        return rateUIs
    }
}