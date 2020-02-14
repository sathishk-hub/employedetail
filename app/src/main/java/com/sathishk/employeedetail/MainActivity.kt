package com.sathishk.employeedetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sathishk.employeedetail.adapter.customAdapter
import com.sathishk.employeedetail.employee.Employee

import com.sathishk.employeedetail.employee.uEmployee
import com.sathishk.employeedetail.viewmodel.employeeViewModel
import com.sathishk.employeedetail.viewmodel.employeeViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val viewModelFactory = employeeViewModelFactory()
        //Use view ModelFactory to initialize view model
        val employeeViewModel = ViewModelProvider(this, viewModelFactory).get(employeeViewModel::class.java)
        //get latest news from view model
        employeeViewModel.getLatestdata()
        //observe viewModel live data
        employeeViewModel.employeeLiveData.observe(this, Observer {
            //bind your ui here
            val users = ArrayList<Employee>()
            users.addAll(it)
            //creating our adapter
            val adapter = customAdapter(users,this)

            //now adding the adapter to recyclerview
            recyclerView.adapter = adapter

        })
        //crating an arraylist to store users using the data class user








        fab.setOnClickListener { view ->

            val changePage = Intent(this, create_employee::class.java)
            // Error: "Please specify constructor invocation;
            // classifier 'Page2' does not have a companion object"

            startActivity(changePage)
            //addPart(em)

        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun partItemClicked(partItem : Employee) {
        Toast.makeText(this, "Clicked: ${partItem.employee_name}", Toast.LENGTH_LONG).show()
    }
}
