package com.ivoxavier.tasks.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.google.gson.Gson
import com.ivoxavier.tasks.R
import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
    private fun failResponse(str : String):String{
        return Gson().fromJson(str,String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>){
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.code() == TaskConstants.HTTP.SUCCESS){
                    val s = ""
                    response.body()?.let { listener.onSuccess(it) }
                } else{
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun isConnectionAvailable(): Boolean{
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapabilites = cm.getNetworkCapabilities(activeNet) ?: return false

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = when {
                netWorkCapabilites.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) -> true
                netWorkCapabilites.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        else{
            if(cm.activeNetworkInfo != null){
                //deprecated
                result = when (cm.activeNetworkInfo!!.type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }

}