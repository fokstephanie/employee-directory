package com.example.employeedirectory.data.remote

import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.data.network.EmployeeApi
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val employeeApi: EmployeeApi
): EmployeeRemoteDataSource {
    override suspend fun fetchEmployees(): List<Employee> {
        val response = employeeApi.getEmployees()

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.employees
        } else {
            throw EmployeeApiError("Could not successfully fetch list of employees")
        }
    }
}

class EmployeeApiError(message: String): Exception(message)