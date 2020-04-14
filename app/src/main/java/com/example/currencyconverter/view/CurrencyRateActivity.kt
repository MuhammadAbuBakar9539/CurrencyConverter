package com.example.currencyconverter.view

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.MyApp
import com.example.currencyconverter.R
import com.example.currencyconverter.common.loadImage
import com.example.currencyconverter.custom.SimpleTextWatcher
import com.example.currencyconverter.data.network.model.Currency
import com.example.currencyconverter.di.component.DaggerCurrencyRateComponent
import com.example.currencyconverter.di.module.CurrencyRateModule
import com.example.currencyconverter.view.adapter.CurrencyRateAdapter
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import kotlinx.android.synthetic.main.activity_currency_rate.*
import kotlinx.android.synthetic.main.currency_rate_item.cimg_currency_flag
import kotlinx.android.synthetic.main.currency_rate_item.tv_currency_full
import kotlinx.android.synthetic.main.currency_rate_item.tv_currency_short
import kotlinx.android.synthetic.main.currency_rate_item_base.*
import javax.inject.Inject

class CurrencyRateActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CurrencyRateViewModel

    private lateinit var currencyAdapter: CurrencyRateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_rate)
        DaggerCurrencyRateComponent.builder().appComponent((application as MyApp).component())
            .currencyRateModule(CurrencyRateModule(this))
            .build().inject(this)

        setupRecyclerView()
        setupEditText()
        setupViewModelObservers()

        viewModel.startFetchingCurrencyRates()
    }

    private fun setupEditText() {
        et_currency_rate.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onAmountChanged(s.toString())
            }
        })
    }

    private fun setupRecyclerView() {
        rv_currency_rate.layoutManager = LinearLayoutManager(this)
        currencyAdapter = CurrencyRateAdapter(mutableListOf()) { currency ->
            viewModel.onCurrencyClicked(currency)
        }
        rv_currency_rate.adapter = currencyAdapter

    }

    private fun setupViewModelObservers() {

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is CurrencyRateViewModel.CurrencyRateState.InProgress -> {
                    pb_currencyRateActivity.visibility = View.VISIBLE
                }

                is CurrencyRateViewModel.CurrencyRateState.FetchRateSuccess -> {
                    pb_currencyRateActivity.visibility = View.GONE
                    currencyAdapter.updateRates(state.rates)
                }

                is CurrencyRateViewModel.CurrencyRateState.Failure -> {
                    pb_currencyRateActivity.visibility = View.GONE
                    Toast.makeText(this@CurrencyRateActivity, state.message, Toast.LENGTH_LONG)
                        .show()
                }
                is CurrencyRateViewModel.CurrencyRateState.UpdateBaseCurrency -> {
                    updateBaseCurrency(state.currency)
                }
            }
        })

    }

    private fun updateBaseCurrency(currency: Currency) {
        tv_currency_short.text = currency.shortName
        tv_currency_full.text = currency.fullName
        et_currency_rate.setText(String.format("%.2f", currency.conversionRate))
        cimg_currency_flag.loadImage(currency.imageUrl)
    }
}
