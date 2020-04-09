package com.example.currencyconverter.di.component

import com.example.currencyconverter.di.module.CurrencyRateModule
import com.example.currencyconverter.di.scope.ActivityScope
import com.example.currencyconverter.view.CurrencyRateActivity
import dagger.Component

@Component(modules = [CurrencyRateModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface CurrencyRateComponent {
    fun inject(currencyRateActivity: CurrencyRateActivity)
}