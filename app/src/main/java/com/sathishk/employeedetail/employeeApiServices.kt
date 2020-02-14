package com.sathishk.employeedetail

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object employeeApiServices {

    // we are creating a networking client using OkHttp.
    private val apiClient = OkHttpClient().newBuilder().build()
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl("http://dummy.restapiexample.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val employeeApi: employeeApiInterface = getRetrofit().create(employeeApiInterface::class.java)
}
