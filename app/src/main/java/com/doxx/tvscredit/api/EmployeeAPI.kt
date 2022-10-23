package com.doxx.tvscredit.api

import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.models.EmployeeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EmployeeAPI {

    @POST("/employee/signin")
    suspend fun signIn(@Body employeeRequest: EmployeeRequest) : Response<EmployeeResponse>

}