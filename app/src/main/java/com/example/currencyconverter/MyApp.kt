package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.di.component.AppComponent
import com.example.currencyconverter.di.component.DaggerAppComponent
import com.example.currencyconverter.di.component.DaggerCurrencyRateComponent
import com.example.currencyconverter.di.module.NetworkModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        component().inject(this)
    }

    fun component(): AppComponent {
        return DaggerAppComponent.builder().networkModule(NetworkModule()).build()
    }
}