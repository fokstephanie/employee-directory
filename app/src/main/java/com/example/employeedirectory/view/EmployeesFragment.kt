package com.example.employeedirectory.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import com.example.employeedirectory.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectory.databinding.MainEmployeeListBinding
import com.example.employeedirectory.view.state.UIState
import com.example.employeedirectory.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Our main/default fragment displaying the list of employees
 */
@AndroidEntryPoint
class EmployeesFragment : Fragment() {

    private var _binding: MainEmployeeListBinding? = null
    private val employeeViewModel: EmployeeViewModel by viewModels()
    private lateinit var adapter: EmployeeListViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainEmployeeListBinding.inflate(inflater, container, false)

        adapter = EmployeeListViewAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refresh.setOnClickListener {

            // TODO: Set onclick to fetch + display list, try to do it for pull-down UI
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                employeeViewModel.uiState.collect {
                    state ->
                    when (state) {
                        is UIState.Loading -> {
                            setViewVisibilities(binding.loadingBar)
                        }
                        is UIState.Empty -> {
                            setViewVisibilities(binding.errorInfoView)
                            binding.errorInfoView.text = resources.getString(R.string.empty_employee_list)
                        }
                        is UIState.Error -> {
                            setViewVisibilities(binding.errorInfoView)
                            binding.errorInfoView.text = resources.getString(R.string.ui_error_message)
                        }
                        is UIState.Success -> {
                            setViewVisibilities(binding.recyclerView)
                            adapter.employees = state.employees
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewVisibilities(view: View) {
        binding.loadingBar.isVisible = true
        binding.recyclerView.isVisible = false
        binding.errorInfoView.isVisible = false

        view.isVisible = true
    }
}