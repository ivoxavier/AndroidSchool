package com.ivoxavier.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ivoxavier.convidados.model.GuestModel
import com.ivoxavier.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val listsAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listsAllGuests

    fun getAll(){
        listsAllGuests.value = repository.getAll()
    }

    fun getPresent(){
        listsAllGuests.value = repository.getPresent()
    }

    fun getAbsent(){
        listsAllGuests.value = repository.getAbsent()
    }

    fun delete(id:Int){
        repository.delete(id)
    }

}