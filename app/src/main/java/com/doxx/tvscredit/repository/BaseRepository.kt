package com.doxx.tvscredit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doxx.tvscredit.api.EmployeeAPI
import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.models.EmployeeResponse
import com.doxx.tvscredit.models.LatLongValue
import com.doxx.tvscredit.models.MobileNumber
import com.doxx.tvscredit.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class BaseRepository @Inject constructor(private val employeeAPI: EmployeeAPI) {
    private val _employeeResponseLD = MutableLiveData<NetworkResult<EmployeeResponse>>()
    val employeeResponseLD : LiveData<NetworkResult<EmployeeResponse>> get() = _employeeResponseLD

    private val _latlongResponseLD = MutableLiveData<NetworkResult<LatLongValue>>()
    val latlongResponseLD : LiveData<NetworkResult<LatLongValue>> get() = _latlongResponseLD

    private val _mobileNoResponseLD = MutableLiveData<NetworkResult<MobileNumber>>()
    val mobileNoResponseLD : LiveData<NetworkResult<MobileNumber>> get() = _mobileNoResponseLD

    suspend fun signIn(employeeRequest: EmployeeRequest){
        _employeeResponseLD.postValue(NetworkResult.Loading())
        val response =  employeeAPI.signIn(employeeRequest)
        handleResponse(response,_employeeResponseLD)
    }

    suspend fun getLatLong(){
        _latlongResponseLD.postValue(NetworkResult.Loading())
        val response = employeeAPI.getLatLong()
        handleResponse(response,_latlongResponseLD)
    }

    suspend fun getMobileNo(){
        _mobileNoResponseLD.postValue(NetworkResult.Loading())
        val response = employeeAPI.getMobileNo()
        handleResponse(response,_mobileNoResponseLD)
    }

    private fun <T> handleResponse(response: Response<T>, liveData : MutableLiveData<NetworkResult<T>>) {
        if(response.isSuccessful && response.body() != null){
            liveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            liveData.postValue(NetworkResult.Error(message = errorObj.getString("message")))
        }
        else liveData.postValue(NetworkResult.Error(message = response.message()))
    }

}