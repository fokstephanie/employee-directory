package com.example.employeedirectory.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeedirectory.MainDispatcherRule
import com.example.employeedirectory.data.remote.fake.FakeEmployeeDataSource
import com.example.employeedirectory.data.repository.EmployeeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var employeeRepository: EmployeeRepository
    private lateinit var employeeDataSource: FakeEmployeeDataSource

    @Before
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        employeeDataSource = FakeEmployeeDataSource(dispatcher)
        employeeRepository = EmployeeRepository() // TODO

        viewModel = EmployeeViewModel(employeeRepository)
    }
}

/**
 * package com.adam.stocks.ui

import com.adam.stocks.MainDispatcherRule
import com.adam.stocks.data.network.fake.FakeRemoteDataSource
import com.adam.stocks.data.network.fake.FakeStocksData
import com.adam.stocks.data.repository.fake.FakeStocksRepository
import com.adam.stocks.domain.GetFormattedStockUseCase
import com.adam.stocks.domain.GetStocksUseCase
import com.adam.stocks.ui.stockslist.StocksUiState
import com.adam.stocks.ui.stockslist.StocksViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StocksViewModelTest {

@get:Rule
val mainDispatcherRule = MainDispatcherRule()

private lateinit var subject: StocksViewModel

private lateinit var stocksRepository: FakeStocksRepository
private lateinit var stocksDataSource: FakeRemoteDataSource
private lateinit var getStocksUseCase: GetStocksUseCase
private val getFormattedStockUseCase = GetFormattedStockUseCase()

@Before
fun setup() {
val dispatcher = StandardTestDispatcher()
stocksDataSource = FakeRemoteDataSource(dispatcher)
stocksRepository = FakeStocksRepository(stocksDataSource, dispatcher)
getStocksUseCase = GetStocksUseCase(stocksRepository, getFormattedStockUseCase)

subject = StocksViewModel(getStocksUseCase)
}

@Test
fun `state is initially loading`() = runTest {
assertEquals(StocksUiState.Loading, subject.uiState.value)
}

@Test
fun `state is success after loading`() = runTest {
val collect = launch(UnconfinedTestDispatcher()) { subject.uiState.collect() }

// Trigger the flow
val expectedStocks = stocksRepository.stocksList.map { stocksList ->
stocksList.map { getFormattedStockUseCase(it) }
}.single()

assertTrue(subject.uiState.value is StocksUiState.Success)

val successState = subject.uiState.value as StocksUiState.Success
assertEquals(expectedStocks, successState.stocks)

collect.cancel()
}

@Test
fun `stats is empty when data source is empty`() = runTest {
stocksDataSource.data = FakeStocksData.emptyStocksData

val collect = launch(UnconfinedTestDispatcher()) { subject.uiState.collect()}

// Trigger the flow
val expectedStocks = stocksRepository.stocksList.map { stocksList ->
stocksList.map { getFormattedStockUseCase(it) }
}.single()

assertTrue(subject.uiState.value is StocksUiState.Empty)
assertTrue(expectedStocks.isEmpty())

collect.cancel()
}
}
 * */