package com.example.todoappciation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MyTodoFactory(private var context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}