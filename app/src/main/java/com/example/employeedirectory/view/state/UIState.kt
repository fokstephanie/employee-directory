package com.example.employeedirectory.view.state

import androidx.annotation.StringRes
import com.example.employeedirectory.model.Employee

sealed class UIState {
        object Loading: UIState()
        object Empty: UIState()
        data class Success(val employees: List<Employee>): UIState()
        data class Error(@StringRes val message: Int): UIState()
}