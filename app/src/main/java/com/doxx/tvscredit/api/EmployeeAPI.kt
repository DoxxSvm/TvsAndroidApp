package com.doxx.tvscredit.api

import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.models.EmployeeResponse
import com.doxx.tvscredit.models.LatLongValue
import com.doxx.tvscredit.models.MobileNumber
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeAPI {

    @POST("/employee/signin")
    suspend fun signIn(@Body employeeRequest: EmployeeRequest) : Response<EmployeeResponse>

    @GET("/employee/getlatlong")
    suspend fun getLatLong():Response<LatLongValue>

    @GET("employee/getmobileno")
    suspend fun getMobileNo():Response<MobileNumber>

}