package com.ivoxavier.tasks.service.repository.remote

import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.model.PriorityModel
import retrofit2.Call
import retrofit2.http.GET

interface PriorityService {

    @GET(TaskConstants.URL.PRIORITY_SERVICE_LIST)
    fun list(): Call<List<PriorityModel>>
}