package com.ivoxavier.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.repository.SecurityPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    //para abastecer os observers
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    fun logout(){
        securityPreferences.remove(TaskConstants.SHARED.TOKEN_KEY)
        securityPreferences.remove(TaskConstants.SHARED.PERSON_KEY)
        securityPreferences.remove(TaskConstants.SHARED.PERSON_NAME)
    }

    fun loadUserName(){
        _name.value = securityPreferences.get(TaskConstants.SHARED.PERSON_NAME)
    }
}