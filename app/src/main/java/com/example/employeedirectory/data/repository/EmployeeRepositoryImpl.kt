package com.example.employeedirectory.data.repository

import com.example.employeedirectory.data.remote.EmployeeRemoteDataSource
import com.example.employeedirectory.di.AppModule
import com.example.employeedirectory.model.Employee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val dataSource: EmployeeRemoteDataSource,
    @AppModule.IoDispatcher private val dispatcher: CoroutineDispatcher
): EmployeeRepository {
    override val employees: Flow<List<Employee>> = flow {
        emit(dataSource.fetchEmployees())
    }.flowOn(dispatcher)
}