package com.example.employeedirectory.data

class EmployeeRepositoryImplTest {
}

/**
 * package com.adam.stocks.data

import com.adam.stocks.MainDispatcherRule
import com.adam.stocks.data.network.fake.FakeRemoteDataSource
import com.adam.stocks.data.network.models.asStock
import com.adam.stocks.data.repository.StocksRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StocksRepositoryImplTest {
private lateinit var subject: StocksRepositoryImpl

@get:Rule
val mainDispatcherRule = MainDispatcherRule()

private lateinit var fakeRemoteDataSource: FakeRemoteDataSource
private lateinit var testDispatcher: CoroutineDispatcher

@Before
fun setup() {
testDispatcher = StandardTestDispatcher()

fakeRemoteDataSource = FakeRemoteDataSource(testDispatcher)
subject = StocksRepositoryImpl(fakeRemoteDataSource, testDispatcher)
}

@Test
fun `repository flow is backed by remote data source` () = runTest {
Assert.assertEquals(
fakeRemoteDataSource.fetchStocks().map { it.asStock() },
subject.stocksList.single()
)
}
}
 *
 *
 *
 * */