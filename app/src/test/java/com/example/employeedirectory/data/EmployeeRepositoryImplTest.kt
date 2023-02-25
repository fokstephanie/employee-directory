package com.example.employeedirectory.data

import com.example.employeedirectory.MainDispatcherRule
import com.example.employeedirectory.data.remote.fake.FakeEmployeeDataSource
import com.example.employeedirectory.data.repository.EmployeeRepositoryImpl
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
class EmployeeRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: EmployeeRepositoryImpl
    private lateinit var fakeEmployeeDataSource: FakeEmployeeDataSource
    private lateinit var testDispatcher: CoroutineDispatcher

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()

        fakeEmployeeDataSource = FakeEmployeeDataSource(testDispatcher)
        repository = EmployeeRepositoryImpl(fakeEmployeeDataSource, testDispatcher)
    }

    @Test
    fun `repository flow is backed by remote data source` () = runTest {
        Assert.assertEquals(
            fakeEmployeeDataSource.fetchEmployees(),
            repository.employees.single()
        )
        // TODO: import runTest properly
    }
}
