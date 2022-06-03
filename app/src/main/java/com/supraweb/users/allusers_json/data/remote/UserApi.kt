package com.supraweb.users.allusers_json.data.remote

import com.supraweb.users.allusers_json.data.remote.dto.UserDtoDetailRemote
import com.supraweb.users.allusers_json.data.remote.dto.UsersDtoRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {


    @GET("/users")
    suspend fun getUsers(): List<UsersDtoRemote>


//    @GET("/posts")
//    suspend fun getPublish(@Query("userId") userId: Int): Response<List<UserDtoDetailRemote>>

    @GET("/posts")
    suspend fun getPublish(@Query("userId") userId: Int): List<UserDtoDetailRemote>

}