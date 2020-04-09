package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.model.CurrencyRateModel
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class CurrencyRateViewModel(private val repository: CurrencyRateRepository) : ViewModel() {
    private lateinit var response: Response<CurrencyRateModel>
    private val currencyRateObservable = MutableLiveData<CurrencyRateModel>()
    private val currencyRateErrorObservable = MutableLiveData<String>()

    fun getCurrencyRate(base: String="EUR") {
        CoroutineScope(Dispatchers.IO).launch {
            response = repository.getCurrencyRate(base)

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        currencyRateObservable.value = response.body()
                    } else {
                        currencyRateErrorObservable.value = response.message()
                    }
                } catch (e: HttpException) {
                    currencyRateErrorObservable.value = e.message
                } catch (e: Throwable) {
                    currencyRateErrorObservable.value = e.message
                }
            }
        }

    }

    fun currencyRateObservable(): LiveData<CurrencyRateModel> {
        return currencyRateObservable
    }

    fun currencyRateErrorObservable(): LiveData<String> {
        return currencyRateErrorObservable
    }
}