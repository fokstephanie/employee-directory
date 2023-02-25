package com.example.employeedirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.employeedirectory.R
import com.example.employeedirectory.model.Employee

class EmployeeListViewAdapter: RecyclerView.Adapter<EmployeeListViewAdapter.ViewHolder>() {
    private lateinit var context: Context

    var employees: List<Employee> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val employeeNameText: TextView = itemView.findViewById(R.id.employeeName)
        val employeeTeamText: TextView = itemView.findViewById(R.id.employeeTeam)
        val employeePhoto: ImageView = itemView.findViewById(R.id.employeePhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context;
        val inflater = LayoutInflater.from(context);
        val contactView = inflater.inflate(R.layout.employee_info_item, parent, false);
        return ViewHolder(contactView);
    }

    override fun getItemCount(): Int {
        return employees.count();
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee: Employee = employees[position]
        val name = employee.name
        val teamName = employee.teamName
        val photoUrl = employee.photoUrlSmall

        val nameTextView = holder.employeeNameText
        val teamTextView = holder.employeeTeamText
        val employeePhoto = holder.employeePhoto

        nameTextView.text = name
        teamTextView.text = teamName

        Glide.with(context)
            .load(photoUrl)
            .placeholder(R.drawable.ic_blank_profile)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(14)))
            .into(employeePhoto);
    }
}