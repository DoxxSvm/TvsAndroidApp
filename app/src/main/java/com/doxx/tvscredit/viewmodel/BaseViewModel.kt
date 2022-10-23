package com.doxx.tvscredit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val repository: BaseRepository) :ViewModel() {
    val employeeResponseLD get() = repository.employeeResponseLD

    fun signIn(employeeRequest: EmployeeRequest){
        viewModelScope.launch {
            repository.signIn(employeeRequest)
        }
    }

}