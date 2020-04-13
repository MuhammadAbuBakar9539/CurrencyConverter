package com.example.currencyconverter.data.network.model

import com.example.currencyconverter.common.getCurrency


data class CurrencyRateResponse(
    val baseCurrency: String,
    val rates: Rates
) {
    fun getCurrencies(): List<Currency> {
        val currencies = ArrayList<Currency>()
        currencies.add(
            "AUD".getCurrency(rates.aud)
        )

        currencies.add(
            "BGN".getCurrency(rates.bgn)
        )

        currencies.add(
            "BRL".getCurrency(rates.brl)
        )

        currencies.add(
            "CAD".getCurrency(rates.cad)
        )

        currencies.add(
            "CHF".getCurrency(rates.chf)
        )

        currencies.add(
            "CNY".getCurrency(rates.cny)
        )

        currencies.add(
            "CZK".getCurrency(rates.czk)
        )

        currencies.add(
            "EUR".getCurrency(rates.eur)
        )

        currencies.add(
            "DKK".getCurrency(rates.dkk)
        )

        currencies.add(
            "GBP".getCurrency(rates.gbp)
        )

        currencies.add(
            "HKD".getCurrency(rates.hkd)
        )

        currencies.add(
            "HRK".getCurrency(rates.hrk)
        )

        currencies.add(
            "HUF".getCurrency(rates.huf)
        )

        currencies.add(
            "IDR".getCurrency(rates.idr)
        )

        currencies.add(
            "ILS".getCurrency(rates.ils)
        )

        currencies.add(
            "INR".getCurrency(rates.inr)
        )

        currencies.add(
            "ISK".getCurrency(rates.isk)
        )

        currencies.add(
            "JPY".getCurrency(rates.jpy)
        )

        currencies.add(
            "KRW".getCurrency(rates.krw)
        )

        currencies.add(
            "MXN".getCurrency(rates.mxn)
        )

        currencies.add(
            "MYR".getCurrency(rates.myr)
        )

        currencies.add(
            "NOK".getCurrency(rates.nok)
        )

        currencies.add(
            "NZD".getCurrency(rates.nzn)
        )

        currencies.add(
            "PHP".getCurrency(rates.php)
        )

        currencies.add(
            "PLN".getCurrency(rates.pln)
        )

        currencies.add(
            "RON".getCurrency(rates.ron)
        )

        currencies.add(
            "RUB".getCurrency(rates.rub)
        )

        currencies.add(
            "SEK".getCurrency(rates.sek)
        )

        currencies.add(
            "SGD".getCurrency(rates.sgd)
        )

        currencies.add(
            "THB".getCurrency(rates.thb)
        )

        currencies.add(
            "USD".getCurrency(rates.usd)
        )

        return currencies
    }
}