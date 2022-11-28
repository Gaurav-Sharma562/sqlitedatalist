package com.example.todoappciation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.bmi.todo_application.databinding.LayoutItemTodoSqliteBinding

class MyRecyclerViewAdapter(val list:List<PersonalData>):RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(var binding:LayoutItemTodoSqliteBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:LayoutItemTodoSqliteBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.layout_item_todo_sqlite,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvTitle.text=list[position].TiTle
        holder.binding.tvDescription.text=list[position].Description
        holder.binding.tvDate.text=list[position].DateTime
        holder.itemView.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->

            contextMenu.setHeaderTitle("Select any Option")
            contextMenu.add(position,101,1,"update")
            contextMenu.add(position,102,2,"delete_item")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}