package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.network.model.CurrencyRateResponse
import com.example.currencyconverter.data.network.model.RateUI
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response

class CurrencyRateViewModel(private val repository: CurrencyRateRepository) : ViewModel() {
    private val _state = MutableLiveData<CurrencyRateState>()
    private val rateUIs = mutableListOf<RateUI>()
    var shortNAme = "EUR"

    val state: LiveData<CurrencyRateState>
        get() {
            return _state
        }

    fun startFetchingCurrencyRate() {
        viewModelScope.launch {
            while (true) {
                getCurrencyRate(shortNAme)
                delay(1000)
            }
        }
    }

    private suspend fun getCurrencyRate(base: String) {
        try {
            _state.postValue(CurrencyRateState.InProgress)
            val responsePair = withContext(Dispatchers.IO) {
                repository.getCurrencyRate(base)
            }
            val currencyRateList = responsePair.first
            val error = responsePair.second

            if (currencyRateList.isNullOrEmpty()) {
                _state.value =
                    CurrencyRateState.Failure(error ?: "Unknown Error Occurred")
            } else {
                rateUIs.clear()
                rateUIs.addAll(currencyRateList)
                _state.value = CurrencyRateState.Success(currencyRateList)
            }
        } catch (e: HttpException) {
            _state.value =
                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occurred")
        } catch (e: Throwable) {
            _state.value =
                CurrencyRateState.Failure(e.localizedMessage ?: "Unknown Error Occurred")
        }

    }

    fun onRateClicked(position: Int) {
        val rateUI = rateUIs[position]
        rateUIs.removeAt(position)
        rateUIs.add(0, rateUI)
        _state.value = CurrencyRateState.Success(rateUIs)
    }

    fun onAmountChanged(changedAmountInString: String) {
        val changedAmount = changedAmountInString.toDoubleOrNull()
        changedAmount?.run {
            val multiplyingFactor = changedAmount / rateUIs[0].conversionRate
            rateUIs.forEach {
                it.conversionRate *= multiplyingFactor
            }
            _state.value = CurrencyRateState.Success(rateUIs)
        }
    }

    sealed class CurrencyRateState {
        object InProgress : CurrencyRateState()
        data class Success(val rates: List<RateUI>) : CurrencyRateState()
        data class Failure(val message: String) : CurrencyRateState()
    }
}