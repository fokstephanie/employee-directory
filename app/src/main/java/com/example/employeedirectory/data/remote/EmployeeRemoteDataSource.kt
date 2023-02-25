package com.example.employeedirectory.data.remote

import com.example.employeedirectory.model.Employee

interface EmployeeRemoteDataSource {
    suspend fun fetchEmployees(): List<Employee>
}