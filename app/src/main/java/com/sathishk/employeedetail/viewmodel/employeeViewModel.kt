package com.sathishk.employeedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sathishk.employeedetail.employee.Employee
import com.sathishk.employeedetail.employeeApiServices
import com.sathishk.employeedetail.employeeRepo

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class employeeViewModel : ViewModel(){
    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val newsRepository : employeeRepo = employeeRepo(employeeApiServices.employeeApi)
    //live data that will be populated as news updates
    val employeeLiveData = MutableLiveData<MutableList<Employee>>()
    fun getLatestdata() {
        ///launch the coroutine scope
        scope.launch {
            //get latest news from news repo
            val latestdata = newsRepository.getLatestdata()
            //post the value inside live data
            employeeLiveData.postValue(latestdata)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()
}