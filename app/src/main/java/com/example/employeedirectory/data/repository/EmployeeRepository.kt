package com.example.employeedirectory.data.repository

import com.example.employeedirectory.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    val employees: Flow<List<Employee>>
}