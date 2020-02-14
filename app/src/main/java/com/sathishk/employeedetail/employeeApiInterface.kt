package com.sathishk.employeedetail


import com.sathishk.employeedetail.employee.Employee
import com.sathishk.employeedetail.employee.LatestData
import com.sathishk.employeedetail.employee.uEmployee
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface employeeApiInterface {
    //fetches latest news with the required query params
    @GET("api/v1/employees")
    fun fetchLatestemployeeAsync(@Query("q") query: String,
                             @Query("sortBy") sortBy : String) : Deferred<Response<LatestData>>

    @POST("api/v1/create") fun addAsync(@Body newEmployee: uEmployee): Deferred<Response<Void>>

    @DELETE("api/v1/delete/{id}") fun deleteAsync(@Path("id") id: Long) : Deferred<Response<Void>>

    @PUT("api/v1/update/{id}") fun updateAsync(@Path("id") id: Long, @Body newEmployee: uEmployee) : Deferred<Response<Void>>
}



