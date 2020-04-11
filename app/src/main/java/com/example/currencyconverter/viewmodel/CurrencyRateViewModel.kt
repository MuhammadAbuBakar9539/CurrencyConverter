package com.example.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.network.model.CurrencyRateResponse
import com.example.currencyconverter.data.network.model.RateUI
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class CurrencyRateViewModel(private val repository: CurrencyRateRepository) : ViewModel() {
    private lateinit var response: Response<CurrencyRateResponse>
    private val _state = MutableLiveData<CurrencyRateState>()
    val rateUIs = mutableListOf<RateUI>()

    val state: LiveData<CurrencyRateState>
        get() {
            return _state
        }

    fun getCurrencyRate(base: String = "EUR") {
        CoroutineScope(Dispatchers.IO).launch {
            _state.postValue(CurrencyRateState.InProgress)
            val responsePair = repository.getCurrencyRate(base)
            val currencyRateList = responsePair.first
            val error = responsePair.second
            withContext(Dispatchers.Main) {
                try {
                    if (currencyRateList.isNullOrEmpty()) {
                        _state.value = CurrencyRateState.Failure(error ?: "Unknown Error Occurred")
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
        }

    }

    fun onRateClicked(position: Int) {
        val rateUI = rateUIs[position]
        rateUIs.removeAt(position)
        rateUIs.add(0, rateUI)
        _state.value = CurrencyRateState.Success(rateUIs)
    }

    sealed class CurrencyRateState {
        object InProgress : CurrencyRateState()
        data class Success(val rates: List<RateUI>) : CurrencyRateState()
        data class Failure(val message: String) : CurrencyRateState()
    }
}