package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.getCurrency
import com.example.currencyconverter.data.network.model.Currency
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import kotlinx.coroutines.*
import retrofit2.HttpException

class CurrencyRateViewModel(private val repository: CurrencyRateRepository) : ViewModel() {
    private val _state = MutableLiveData<CurrencyRateState>()
    val currency = mutableListOf<Currency>()
    var previousAmount: Double? = null
    private var selectedCurrency: String = "EUR"
    private var dirtyFlag = false
    val state: LiveData<CurrencyRateState>
        get() {
            return _state
        }

    fun startFetchingCurrencyRates() {
        viewModelScope.launch {
            while (true) {
                dirtyFlag = false
                val result = getCurrencyRate(selectedCurrency)
                if (!dirtyFlag) {
                    _state.value = result
                }
                delay(1000)
            }
        }
    }

    private suspend fun getCurrencyRate(base: String): CurrencyRateState {
        return withContext(Dispatchers.IO) {
            try {
                _state.postValue(CurrencyRateState.InProgress)
                val responsePair =
                    repository.getCurrencyRate(base)

                val currencyRateList = responsePair.first
                val error = responsePair.second
                if (currencyRateList.isNullOrEmpty()) {
                    CurrencyRateState.Failure(
                        error ?: "Unknown Error Occured"
                    )
                } else {
                    prepareData(currencyRateList.toMutableList())

                }
            } catch (e: HttpException) {

                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occured")
            } catch (e: Throwable) {

                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occured")
            }
        }
    }


    private fun prepareData(currencyRateList: List<Currency>): CurrencyRateState.Success {
        currencyRateList.forEach {
            it.conversionRate *= previousAmount ?: 1.0
        }
        currency.clear()
        currency.add(selectedCurrency.getCurrency(previousAmount ?: 1.0))
        currency.addAll(currencyRateList.filter { it.shortName != selectedCurrency })
        return CurrencyRateState.Success(currency)
    }

    fun onCurrencyClicked(currency: Currency) {
        dirtyFlag = true
        selectedCurrency = currency.shortName
        previousAmount = currency.conversionRate

    }

    fun onAmountChanged(changedAmountInString: String) {
        dirtyFlag = true
        if (changedAmountInString.trim() != "") {
            val oldAmount = previousAmount ?: 1.0
            previousAmount = changedAmountInString.toDoubleOrNull()
            previousAmount?.run {

                currency.forEachIndexed { index, rate ->
                    if (index != 0) {
                        rate.conversionRate *= ((previousAmount) ?: 1.0) / oldAmount
                    }
                }
                _state.value = CurrencyRateState.Success(currency)
            }
        }
    }


    sealed class CurrencyRateState {
        object InProgress : CurrencyRateState()
        data class Success(val rates: List<Currency>) : CurrencyRateState()
        data class Failure(val message: String) : CurrencyRateState()
    }
}