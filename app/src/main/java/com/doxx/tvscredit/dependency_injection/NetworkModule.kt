package com.doxx.tvscredit.dependency_injection

import com.doxx.tvscredit.api.AuthIntercepter
import com.doxx.tvscredit.api.EmployeeAPI
import com.doxx.tvscredit.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

    }

    @Singleton
    @Provides
    fun getEmployeeAPI(retrofitBuilder: Retrofit.Builder):EmployeeAPI{
        return retrofitBuilder.build().create(EmployeeAPI::class.java)
    }

    @Singleton
    @Provides
    fun getOkHttpClient(authIntercepter: AuthIntercepter): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authIntercepter).build()
    }



}