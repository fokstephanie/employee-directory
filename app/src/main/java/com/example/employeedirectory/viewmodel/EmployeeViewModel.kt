package com.example.employeedirectory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.R
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.view.asResult
import com.example.employeedirectory.view.Result
import com.example.employeedirectory.view.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    employeeRepository: EmployeeRepository,
): ViewModel() {
    val uiState: StateFlow<UIState> = // TODO: Reasoning for using a state flow?
        employeeRepository.employees.asResult().map { result ->
            when (result) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        UIState.Empty
                    } else {
                        UIState.Success(result.data)
                    }
                }
                is Result.Loading -> {
                    UIState.Loading
                }
                is Result.Error -> {
                    UIState.Error(R.string.ui_error_message)
                }
            }
        }.stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            UIState.Loading
        )
}