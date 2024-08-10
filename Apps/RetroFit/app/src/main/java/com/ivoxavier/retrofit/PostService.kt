package com.ivoxavier.retrofit

import retrofit2.Call
import retrofit2.http.GET


/*
*  https://jsonplaceholder.typicode.com/posts
*  https://jsonplaceholder.typicode.com/users
*  https://jsonplaceholder.typicode.com/albums
*
* */

//teria de adicionar um service para cada entidade AlbumbsService

interface PostService {

    @GET("posts")
    fun list(): Call<List<PostEntity>>
}