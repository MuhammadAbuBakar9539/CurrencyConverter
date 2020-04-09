package com.example.currencyconverter.di.component

import com.example.currencyconverter.MyApp
import com.example.currencyconverter.common.network.Client
import com.example.currencyconverter.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(myApp: MyApp)
    fun client():Client
}