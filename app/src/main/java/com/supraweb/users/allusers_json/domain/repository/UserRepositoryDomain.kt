package com.supraweb.users.allusers_json.domain.repository

import com.supraweb.users.allusers_json.data.remote.dto.UserDtoDetailRemote
import com.supraweb.users.allusers_json.domain.model.UserModelDomain
 import com.supraweb.users.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepositoryDomain {

    //    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

    fun getAllUserRepositoryDomai() : Flow<Resource<List<UserModelDomain>>>


    /*
       @GET("/posts")
    suspend fun getPublish(@Query("userId") userId: Int): Response<List<UserDtoDetailRemote>>

      suspend fun getPublish(@Query("userId") userId: Int): List<UserDtoDetailRemote>
     */

    suspend fun getPublish(userId: Int): List<UserDtoDetailRemote>



}