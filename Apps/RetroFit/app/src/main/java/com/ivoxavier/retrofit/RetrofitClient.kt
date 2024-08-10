package com.ivoxavier.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        //Singleton
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
           val http = OkHttpClient.Builder()
            if(!::INSTANCE.isInitialized){
                INSTANCE = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createService(call: Class<S>): S {
            return getRetrofitInstance().create(call)
        }



    }


}