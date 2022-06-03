package com.supraweb.users.allusers_json.domain.usecase

import com.supraweb.users.allusers_json.data.remote.dto.toUserModelDetail
import com.supraweb.users.allusers_json.domain.model.UserModelDetail
import com.supraweb.users.allusers_json.domain.repository.UserRepositoryDomain
import com.supraweb.users.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailsUseCase        @Inject constructor(
    private val repository: UserRepositoryDomain


)       {
//UserDtoDetailRemote
    operator fun invoke(userId : Int): Flow<Resource<List<UserModelDetail>>> = flow {

    try {
        emit(Resource.Loading<List<UserModelDetail>>())
        val publish = repository.getPublish(userId).map { it.toUserModelDetail() }
        emit(Resource.Success<List<UserModelDetail>>(publish))


    }catch(e: HttpException) {
        emit(Resource.Error<List<UserModelDetail>>(e.localizedMessage ?: "An unexpected error occured"))


    }catch(e: IOException) {

        emit(Resource.Error<List<UserModelDetail>>("Couldn't reach server. Check your internet connection."))


    }




    }
}