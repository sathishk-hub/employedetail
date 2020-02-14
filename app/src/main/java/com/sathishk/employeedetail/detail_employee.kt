package com.sathishk.employeedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sathishk.employeedetail.employee.uEmployee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class detail_employee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_employee)
        val id:String = intent.getStringExtra("id")
        val name:String = intent.getStringExtra("name")
        val age:String = intent.getStringExtra("age")
        val salary:String = intent.getStringExtra("salary")
        Log.d("got",intent.getStringExtra("id"))

        val view_name =findViewById(R.id.ename)as TextInputEditText
        val view_age =findViewById(R.id.eage)as TextInputEditText
        val view_salary =findViewById(R.id.esalary)as TextInputEditText

        view_age.setText(age)
        view_name.setText(name)
        view_salary.setText(salary)

        val btn_update = findViewById(R.id.update) as Button
        val btn_delete = findViewById(R.id.delete) as Button
        // set on-click listener
        btn_update.setOnClickListener {
            var em =uEmployee(name,salary,age)
            // your code to perform when the user clicks on the button
            update(id.toLong(),em)
        }
        btn_delete.setOnClickListener {
            // your code to perform when the user clicks on the button
        delete(id.toLong())
        }
    }
    private fun delete(itemId : Long) {
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = employeeApiServices.employeeApi.deleteAsync(itemId).await()
            Log.d("got", "Delete success: ${webResponse}")

            if(webResponse.isSuccessful){
                dialog("Deleted Seccessfully!")
            }
            else{
                dialog("Employee not deleted try again!")
            }

        }
    }

    private fun update(originalItemId: Long, newItem: uEmployee) {
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = employeeApiServices.employeeApi.updateAsync(originalItemId, newItem).await()
            Log.d("got", "update success: ${webResponse}")

            if(webResponse.isSuccessful){
                dialog("Updated Seccessfully!")
            }
            else{
                dialog("Employee not updated try again!")
            }

        }
    }
private fun dialog(message:String){
    Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
}

}
