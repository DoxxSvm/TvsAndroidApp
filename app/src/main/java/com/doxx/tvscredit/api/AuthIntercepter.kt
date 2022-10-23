package com.doxx.tvscredit.api

import com.doxx.tvscredit.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthIntercepter @Inject constructor() : Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =chain.request().newBuilder()
        val token = tokenManager.getToken()
        request.addHeader("Authorization","Bearer $token")
        return chain.proceed(request.build())
    }
}