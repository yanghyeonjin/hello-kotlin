package com.yanghyeonjin.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yanghyeonjin.hellokotlin.R
import com.yanghyeonjin.hellokotlin.model.Employee

class EmployeeAdapter(private val employeeList: ArrayList<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivGender: ImageView = itemView.findViewById(R.id.ivProfile)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAge: TextView = itemView.findViewById(R.id.tvAge)
        val tvJob: TextView = itemView.findViewById(R.id.tvJob)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)

        return EmployeeViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val profile: Employee = employeeList[curPos]

                Toast.makeText(parent.context, "이름: ${profile.name} / 직업: ${profile.job}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        when(employeeList[position].gender) {
            0 -> holder.ivGender.setImageResource(R.drawable.ic_man)
            1 -> holder.ivGender.setImageResource(R.drawable.ic_woman)
        }

        holder.tvName.text = employeeList[position].name
        holder.tvAge.text = employeeList[position].age.toString()
        holder.tvJob.text = employeeList[position].job
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}