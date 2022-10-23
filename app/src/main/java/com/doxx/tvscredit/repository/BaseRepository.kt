package com.doxx.tvscredit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doxx.tvscredit.api.EmployeeAPI
import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.models.EmployeeResponse
import com.doxx.tvscredit.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class BaseRepository @Inject constructor(private val employeeAPI: EmployeeAPI) {
    private val _employeeResponseLD = MutableLiveData<NetworkResult<EmployeeResponse>>()
    val employeeResponseLD : LiveData<NetworkResult<EmployeeResponse>> get() = _employeeResponseLD

    suspend fun signIn(employeeRequest: EmployeeRequest){
        _employeeResponseLD.postValue(NetworkResult.Loading())
        val response =  employeeAPI.signIn(employeeRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<EmployeeResponse>) {
        if(response.isSuccessful && response.body() != null){
            _employeeResponseLD.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _employeeResponseLD.postValue(NetworkResult.Error(message = errorObj.getString("message")))
        }
        else _employeeResponseLD.postValue(NetworkResult.Error(message = response.message()))
    }

}