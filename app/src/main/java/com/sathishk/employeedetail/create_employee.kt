package com.sathishk.employeedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.sathishk.employeedetail.employee.uEmployee
import kotlinx.android.synthetic.main.activity_create_employee.*
import kotlinx.android.synthetic.main.activity_create_employee.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class create_employee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_employee)
        val btn_click_me = findViewById(R.id.save) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {
            // your code to perform when the user clicks on the button
           addemploye(uEmployee(ename.toString(),esalary.toString(),eage.toString()))
        }

    }
    private fun addemploye(employeeItem: uEmployee) {
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = employeeApiServices.employeeApi.addAsync(employeeItem).await()
            Log.d("got", "Add success: ${webResponse}")
            if(webResponse.isSuccessful){
                dialog("Created Employee!")
            }
            else{
                dialog("Employee not created try again!")
            }

        }
    }
    private fun dialog(message:String){
        Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}


