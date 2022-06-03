package com.supraweb.users.allusers_json.domain.usecase

import com.supraweb.users.allusers_json.domain.model.UserModelDomain
import com.supraweb.users.allusers_json.domain.repository.UserRepositoryDomain
import com.supraweb.users.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersListUseCase     @Inject constructor(

private val repository: UserRepositoryDomain

)


{

    operator fun invoke() : Flow<Resource<List<UserModelDomain>>> {
               if(repository.getAllUserRepositoryDomai()==null){
                   return flow {  }
               }
        return repository.getAllUserRepositoryDomai()

    }



}


//GetUserDetailsUseCase