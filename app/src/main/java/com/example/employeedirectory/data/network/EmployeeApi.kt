package com.example.employeedirectory.data.network

import com.example.employeedirectory.model.EmployeeList
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("/sq-mobile-interview/employees.json") // TODO: Change this string to get other jsons
    suspend fun getEmployees(): Response<EmployeeList>
}

// employees_malformed // TODO: Malformed doesnt return empty as it should, this should be changed
// employees
// employees_empty