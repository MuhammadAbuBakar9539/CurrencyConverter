package com.example.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.currencyconverter.MyApp
import com.example.currencyconverter.R
import com.example.currencyconverter.view.recyclerview.RateUI
import com.example.currencyconverter.di.component.DaggerCurrencyRateComponent
import com.example.currencyconverter.di.module.CurrencyRateModule
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import kotlinx.android.synthetic.main.activity_currency_rate.*
import javax.inject.Inject

class CurrencyRateActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CurrencyRateViewModel
    private val rateUi =
        RateUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_rate)

        DaggerCurrencyRateComponent.builder().appComponent((application as MyApp).component())
            .currencyRateModule(CurrencyRateModule(this))
            .build().inject(this)

        viewModel.getCurrencyRate()

        viewModel.currencyRateObservable().observe(this, Observer { rate->
            tv_currency_short.text = rateUi.shortNameList["AUD"]
            tv_currency_full.text = rateUi.fullNameList["AUD"]
            tv_currency_rate.text = rate.rates.aUD.toString()
        })
    }
}
