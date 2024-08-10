package com.ivoxavier.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivoxavier.convidados.model.GuestModel
import com.ivoxavier.convidados.repository.GuestRepository

//o AndroidViewModel tem contexto, a ViewModel nao
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun save(guest: GuestModel){
        if(guest.id == 0){
           _saveGuest.value = repository.insert(guest)
        }else{
            _saveGuest.value = repository.update(guest)
        }
    }

    fun get(id: Int){
      guestModel.value =  repository.get(id)
    }

}