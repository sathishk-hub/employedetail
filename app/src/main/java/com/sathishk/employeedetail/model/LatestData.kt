package com.sathishk.employeedetail.employee

import com.squareup.moshi.JsonClass

data class LatestData(

    val data: List<Employee>,
    val status: String

   )
@JsonClass(generateAdapter = true)
data class Employee(
    val id:String,
    val employee_name:String,
    val employee_salary: String,
    val employee_age:String
)
@JsonClass(generateAdapter = true)
data class uEmployee(

    val employee_name:String,
    val employee_salary: String,
    val employee_age:String
)