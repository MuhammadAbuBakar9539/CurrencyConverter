package com.example.currencyconverter.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.common.network.Client
import com.example.currencyconverter.di.scope.ActivityScope
import com.example.currencyconverter.view.CurrencyRateActivity
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepositoryImpl
import com.example.currencyconverter.viewmodel.repository.CurrencyRateViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CurrencyRateModule(private val currencyRateActivity: CurrencyRateActivity) {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(repository: CurrencyRateRepository): CurrencyRateViewModelFactory {
        return CurrencyRateViewModelFactory(
            repository
        )
    }

    @Provides
    @ActivityScope
    fun provideViewModel(factory: CurrencyRateViewModelFactory): CurrencyRateViewModel {
        return ViewModelProvider(currencyRateActivity, factory).get(CurrencyRateViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun provideRepository(client: Client): CurrencyRateRepository {
        return CurrencyRateRepositoryImpl(client)
    }
}