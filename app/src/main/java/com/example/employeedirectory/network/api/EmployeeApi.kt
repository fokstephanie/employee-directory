package com.example.employeedirectory.network.api

import com.example.employeedirectory.model.EmployeeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeApi {
    @GET("/sq-mobile-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeList>
}