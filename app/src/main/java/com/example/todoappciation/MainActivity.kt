package com.example.todoappciation

import android.app.ActionBar
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappciation.databinding.ActivityMainTodoSqliteBinding
import com.example.todoappciation.databinding.LayoutUpdateTodoSqliteBinding
import com.example.todoappciation.databinding.TableLayoutTodosqliteBinding


import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainTodoSqliteBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var factory: MyTodoFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_todo_sqlite)
        factory= MyTodoFactory(this)
        viewModel=ViewModelProvider(this,factory)[MyViewModel::class.java]
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        val dataList=viewModel.getDataList()
        Log.d("DATALIST",""+dataList)
        val adapter= MyRecyclerViewAdapter(dataList)
        binding.recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
        registerForContextMenu(binding.recyclerView)
        binding.floatingButton.setOnClickListener {

            var tableLayoutBinding: TableLayoutTodosqliteBinding =DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.table_layout_todosqlite,null,false)
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(tableLayoutBinding.root)
            dialog.setCancelable(false)
            dialog.show()
            val window=dialog.window
            window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT)
            tableLayoutBinding.create.setOnClickListener {
                viewModel.createData(tableLayoutBinding.title.text.toString(),tableLayoutBinding.desc.text.toString(),viewModel.dateTime().toString())
                dialog.dismiss()

            }

            tableLayoutBinding.dismiss.setOnClickListener {
                dialog.dismiss()
            }
        }

    }



    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            101-> {
                val updateBinding: LayoutUpdateTodoSqliteBinding =DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.layout_update_todo_sqlite,null,false)
                val dialog = Dialog(this)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(updateBinding.root)
                dialog.setCancelable(false)
                dialog.show()
                val window=dialog.window
                window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT)
                //  window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                updateBinding.update.setOnClickListener{
                    viewModel.updateData(updateBinding.updateTitle.text.toString(),updateBinding.updateDescription.text.toString(),viewModel.dateTime())
                    binding.recyclerView.adapter
                    dialog.dismiss()
                }

            }
            102 ->{

            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.example_menu_todo_sqlite,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_all){
            viewModel.deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }
}