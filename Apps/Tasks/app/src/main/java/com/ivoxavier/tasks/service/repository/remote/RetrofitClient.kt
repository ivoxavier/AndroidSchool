package com.ivoxavier.tasks.service.repository.remote


import com.ivoxavier.tasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){
    companion object{

        private lateinit var INSTANCE: Retrofit
        private var token: String = ""
        private var personKey: String = ""

        private fun getRetrofitInstance(): Retrofit{
           val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                        .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                        .build()

                   return chain.proceed(request)
                }

            })


            //verifica se nao foi inicializado
            if(!::INSTANCE.isInitialized){
                synchronized(RetrofitClient::class){
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(TaskConstants.URL.URL_API)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
           return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)

        }

        fun addHeaders(tokenValue: String, personKeyValue:String){
            token = tokenValue
            personKey = personKeyValue

        }

    }
}