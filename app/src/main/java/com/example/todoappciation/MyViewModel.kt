package com.example.todoappciation

import android.content.Context
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MyViewModel(private var context: Context):ViewModel() {
    val dbRepository=DBRepository(context)
    fun createData(TiTle:String,Description:String,DateTime:String){
        dbRepository.createData(TiTle,Description,DateTime)
    }
    fun getDataList():List<PersonalData>{
        return dbRepository.getPersonalData()
    }
    fun deleteAll(){
        dbRepository.deleteAll()
    }
    fun dateTime() : String{
        return dbRepository.dateTime()
    }
    fun updateData(TiTle:String,Description:String,DateTime:String){
        dbRepository.updateData(TiTle, Description, DateTime)
    }
    fun deleteData(TiTle:String,Description:String,DateTime:String){
        dbRepository.deleteData(TiTle, Description, DateTime)
    }
}


