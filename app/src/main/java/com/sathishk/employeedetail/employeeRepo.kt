package com.sathishk.employeedetail

import com.sathishk.employeedetail.employee.Employee


class employeeRepo (private val apiInterface: employeeApiInterface) : BaseRepository() {
        //get latest news using safe api call
        suspend fun getLatestdata() :  MutableList<Employee>?{
            return safeApiCall(
                //await the result of deferred type
                call = {apiInterface.fetchLatestemployeeAsync("data", "publishedAt").await()},
                error = "Error fetching data"
                //convert to mutable list
            )?.data?.toMutableList()
        }
    }
