package com.example.employeedirectory.data.remote.fake

import com.example.employeedirectory.data.remote.EmployeeRemoteDataSource
import com.example.employeedirectory.di.AppModule
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.model.EmployeeList
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeEmployeeDataSource @Inject constructor(
    @AppModule.IoDispatcher private val dispatcher: CoroutineDispatcher,
    var data: String = FakeEmployeeData.validEmployees,
): EmployeeRemoteDataSource {
    override suspend fun fetchEmployees(): List<Employee> =
        withContext(dispatcher) {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(EmployeeList::class.java)

            adapter.fromJson(data)?.employees ?: run {
                emptyList()
            }
        }
}