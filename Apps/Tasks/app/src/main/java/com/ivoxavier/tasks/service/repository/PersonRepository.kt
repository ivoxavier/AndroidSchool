package com.ivoxavier.tasks.service.repository

import android.content.Context
import com.google.gson.Gson
import com.ivoxavier.tasks.R
import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.listener.APIListener
import com.ivoxavier.tasks.service.model.PersonModel
import com.ivoxavier.tasks.service.repository.remote.PersonService
import com.ivoxavier.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonRepository(context: Context): BaseRepository(context) {

  private  val remote = RetrofitClient.getService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener<PersonModel>) {
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.login(email, password), listener)
    }

    fun create(name: String, email: String, password: String, listener: APIListener<PersonModel>) {
        if(!isConnectionAvailable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.create(name,email, password), listener)
    }
}