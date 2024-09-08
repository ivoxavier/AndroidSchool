package com.ivoxavier.tasks.service.repository

import android.content.Context
import com.google.gson.Gson
import com.ivoxavier.tasks.R
import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.listener.APIListener
import com.ivoxavier.tasks.service.model.PriorityModel
import com.ivoxavier.tasks.service.repository.local.TaskDatabase
import com.ivoxavier.tasks.service.repository.remote.PriorityService
import com.ivoxavier.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    companion object{
        private val cache = mutableMapOf<Int,String>()
        fun getDescription(id: Int): String{
            return cache[id] ?: ""
        }
        fun setDescription(id: Int, str: String){
            cache[id] = str
        }
    }

    fun list(listener: APIListener<List<PriorityModel>>){
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.list(), listener)
    }

    fun list(): List<PriorityModel>{
        return database.list()
    }


    //Cache


    fun getDescription(id: Int): String{
        val cached = PriorityRepository.getDescription(id)
       return if(cached == ""){
           val description = database.getDescription(id)
            PriorityRepository.setDescription(id,description)
            return description
        }else{
            cached
        }
    }

    fun save(list: List<PriorityModel>){
        database.clear()
        database.save(list)
    }
}