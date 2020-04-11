package com.example.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.MyApp
import com.example.currencyconverter.R
import com.example.currencyconverter.di.component.DaggerCurrencyRateComponent
import com.example.currencyconverter.di.module.CurrencyRateModule
import com.example.currencyconverter.view.adapter.CurrencyRateAdapter
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import kotlinx.android.synthetic.main.activity_currency_rate.*
import javax.inject.Inject

class CurrencyRateActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CurrencyRateViewModel

    lateinit var currencyAdapter: CurrencyRateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_rate)

        DaggerCurrencyRateComponent.builder().appComponent((application as MyApp).component())
            .currencyRateModule(CurrencyRateModule(this))
            .build().inject(this)

        rv_currency_rate.layoutManager = LinearLayoutManager(this)
        currencyAdapter = CurrencyRateAdapter(mutableListOf()){position->
            viewModel.onRateClicked(position)
        }
        rv_currency_rate.adapter = currencyAdapter


        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is CurrencyRateViewModel.CurrencyRateState.InProgress -> {

                }

                is CurrencyRateViewModel.CurrencyRateState.Success -> {
                    currencyAdapter.updateRates(state.rates)
                }

                is CurrencyRateViewModel.CurrencyRateState.Failure -> {
                    Toast.makeText(this@CurrencyRateActivity, state.message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

        viewModel.getCurrencyRate()
    }
}
