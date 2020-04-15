package com.example.currencyconverter

import com.example.currencyconverter.viewmodel.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class TestCoroutineRule: TestRule{
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
    override fun apply(base: Statement?, description: Description?)= object: Statement(){
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)
            base?.evaluate()
            Dispatchers.resetMain()
            testCoroutineScope.cleanupTestCoroutines()
        }
    }
}
class TestContextProvider: CoroutineContextProvider(){
    override val IO: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val Main: CoroutineContext by lazy { Dispatchers.Unconfined }
}
