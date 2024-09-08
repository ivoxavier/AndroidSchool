package com.ivoxavier.tasks.service.repository.remote

import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.model.PersonModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {

    @POST(TaskConstants.URL.PERSON_SERVICE_LOGIN)
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<PersonModel> //retorno do tipo PersonModel

    @POST(TaskConstants.URL.PERSON_SERVICE_CREATE)
    @FormUrlEncoded
    fun create(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<PersonModel> //retorno do tipo PersonModel
}