package com.sathishk.employeedetail.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sathishk.employeedetail.R
import com.sathishk.employeedetail.detail_employee
import com.sathishk.employeedetail.employee.Employee


class customAdapter(val userList: ArrayList<Employee>,context: Context) : RecyclerView.Adapter<customAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, detail_employee::class.java)
            intent.putExtra("id",userList[position].id)
            Log.d("got",userList[position].id)
            intent.putExtra("name",userList[position].employee_name)
            intent.putExtra("age",userList[position].employee_age)
            intent.putExtra("salary",userList[position].employee_salary)

            view.context.startActivity(intent);
        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(employeeData: Employee) {
            val textViewName = itemView.findViewById(R.id.textViewName) as TextView

            textViewName.text = employeeData.employee_name

        }
    }
}