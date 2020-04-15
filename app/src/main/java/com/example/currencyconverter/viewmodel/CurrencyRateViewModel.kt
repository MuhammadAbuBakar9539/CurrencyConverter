package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.getCurrency
import com.example.currencyconverter.data.network.model.Currency
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CurrencyRateViewModel(
    private val repository: CurrencyRateRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {
    private val _state = MutableLiveData<CurrencyRateState>()
    private var currency = mutableListOf<Currency>()
    private var previousAmount: Double? = null
    private var dirtyFlag = false
    private var baseCurrency: Currency = "EUR".getCurrency(1.0)
    val state: LiveData<CurrencyRateState>
        get() {
            return _state
        }

    fun initializeBaseCurrency() {
        _state.value = CurrencyRateState.UpdateBaseCurrency(baseCurrency)
    }

    fun fetchCurrencyRate() {

        viewModelScope.launch {
            dirtyFlag = false
            val result = getCurrencyRate(baseCurrency.shortName)
            if (!dirtyFlag) {
                _state.value = result
            }
            delay(1000)
            _state.value = CurrencyRateState.ReadyToFetchAgain
        }
    }

    private suspend fun getCurrencyRate(base: String): CurrencyRateState {
        return withContext(coroutineContextProvider.IO) {
            try {
                _state.postValue(CurrencyRateState.InProgress)
                val responsePair =
                    repository.getCurrencyRate(base)

                val currencyRateList = responsePair.first
                val error = responsePair.second
                if (currencyRateList.isNullOrEmpty()) {
                    CurrencyRateState.Failure(
                        error ?: "Unknown Error Occurred"
                    )
                } else {
                    prepareData(currencyRateList.toMutableList())

                }
            } catch (e: HttpException) {

                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occurred")
            } catch (e: Throwable) {

                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occurred")
            }
        }
    }


    private fun prepareData(currencyRateList: List<Currency>): CurrencyRateState.FetchRateSuccess {
        val updatedList = currencyRateList.filter { it.shortName != baseCurrency.shortName }.map {
            it.conversionRate *= previousAmount ?: 1.0
            it
        }

        currency = updatedList.toMutableList()
        return CurrencyRateState.FetchRateSuccess(currency)
    }

    fun onCurrencyClicked(currency: Currency) {
        dirtyFlag = true
        previousAmount = currency.conversionRate
        baseCurrency = currency
        _state.value = CurrencyRateState.UpdateBaseCurrency(currency)
    }

    fun onAmountChanged(changedAmountInString: String) {
        dirtyFlag = true
        if (changedAmountInString.trim() != "") {
            val oldAmount = previousAmount ?: 1.0
            previousAmount = changedAmountInString.toDoubleOrNull()
            previousAmount?.run {

                currency.forEach { rate ->
                    rate.conversionRate *= ((previousAmount) ?: 1.0) / oldAmount
                }
                _state.value = CurrencyRateState.FetchRateSuccess(currency)
            }
        }
    }

    sealed class CurrencyRateState {
        object InProgress : CurrencyRateState()
        object ReadyToFetchAgain : CurrencyRateState()
        data class FetchRateSuccess(val rates: List<Currency>) : CurrencyRateState()
        data class Failure(val message: String) : CurrencyRateState()
        data class UpdateBaseCurrency(val currency: Currency) : CurrencyRateState()
    }
}