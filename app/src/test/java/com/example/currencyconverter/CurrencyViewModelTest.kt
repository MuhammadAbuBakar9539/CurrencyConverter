package com.example.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.getCurrency
import com.example.currencyconverter.data.network.model.Currency
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel
import com.example.currencyconverter.viewmodel.repository.CurrencyRateRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@ExperimentalCoroutinesApi
@RunWith(BlockJUnit4ClassRunner::class)
class CurrencyViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @MockK
    lateinit var currencyRateRepository: CurrencyRateRepository

    lateinit var viewModel: CurrencyRateViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = CurrencyRateViewModel(currencyRateRepository, TestContextProvider())
    }

    @Test
    fun `when setupInitialCurrency called state should be of UpdateBaseCurrency with Euro`() {
        viewModel.initializeBaseCurrency()
        assertEquals(
            "EUR".getCurrency(),
            (viewModel.state.value as CurrencyRateViewModel.CurrencyRateState.UpdateBaseCurrency).currency
        )
    }

    @Test
    fun `when onCurrencyClicked called state should be of UpdateBaseCurrency with currency provided to the method`() {
        viewModel.onCurrencyClicked("USD".getCurrency())
        assertEquals(
            "USD".getCurrency(),
            (viewModel.state.value as CurrencyRateViewModel.CurrencyRateState.UpdateBaseCurrency).currency
        )
    }

    @Test
    fun `when currencyRepository returns success object the viewModel should get currencyList without including base currency`() {
        val euro = Currency("EUR", "EURO", "", 1.0)
        val usd = Currency("USD", "US Dollar", "", 1.2)
        viewModel.onCurrencyClicked(euro)
        val currencyList = listOf(euro, usd)
        val expectedCurrency = listOf(usd)
        coEvery {
            currencyRateRepository.getCurrencyRate(any())
        }.returns(Pair(currencyList, null))
        viewModel.fetchCurrencyRate()
        val state = viewModel.state.value
        assert(state is CurrencyRateViewModel.CurrencyRateState.FetchRateSuccess)
        assertEquals(
            expectedCurrency,
            (state as CurrencyRateViewModel.CurrencyRateState.FetchRateSuccess).rates
        )
    }

    @Test
    fun `when currencyRepository returns success object and user changes the amount then the viewModel should get currencyList without including base currency and properly updated converted values`() {
        val euro = Currency("EUR", "EURO", "", 1.0)
        val usd = Currency("USD", "US Dollar", "", 1.2)
        viewModel.onCurrencyClicked(euro)
        val currencyList = listOf(euro, usd)
        coEvery {
            currencyRateRepository.getCurrencyRate(any())
        }.returns(Pair(currencyList, null))
        viewModel.fetchCurrencyRate()
        Thread.sleep(1000)
        viewModel.onAmountChanged("2.0")
        val state = viewModel.state.value
        assert(state is CurrencyRateViewModel.CurrencyRateState.FetchRateSuccess)
        assertEquals(
            2.4,
            (state as CurrencyRateViewModel.CurrencyRateState.FetchRateSuccess).rates.first().conversionRate
        )
    }

    @Test
    fun `when currencyRepository returns exception with localized message  the viewModel should return underlying exception message`() {
        coEvery {
            currencyRateRepository.getCurrencyRate(any())
        }.throws(RuntimeException("Sample Error Message"))
        viewModel.fetchCurrencyRate()
        val state = viewModel.state.value
        assert(state is CurrencyRateViewModel.CurrencyRateState.Failure)
        assertEquals(
            "Sample Error Message",
            (state as CurrencyRateViewModel.CurrencyRateState.Failure).message
        )
    }

    @Test
    fun `when currencyRepository returns exception without localized message  the viewModel should return Unknown Error Occurred`() {
        coEvery {
            currencyRateRepository.getCurrencyRate(any())
        }.throws(RuntimeException())
        viewModel.fetchCurrencyRate()
        val state = viewModel.state.value
        assert(state is CurrencyRateViewModel.CurrencyRateState.Failure)
        assertEquals(
            "Unknown Error Occurred",
            (state as CurrencyRateViewModel.CurrencyRateState.Failure).message
        )
    }

    @After
    fun tearDown() {
        viewModel.viewModelScope.cancel()
    }

}