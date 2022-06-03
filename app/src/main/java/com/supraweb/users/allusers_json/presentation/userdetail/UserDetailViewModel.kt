package com.supraweb.users.allusers_json.presentation.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.supraweb.users.allusers_json.domain.model.UserModelDetail
import com.supraweb.users.allusers_json.domain.usecase.GetUserDetailsUseCase
import com.supraweb.users.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
private val getUserDetailsUseCase: GetUserDetailsUseCase

) : ViewModel(){

        val userDetail =getUserDetailsUseCase.invoke(0).asLiveData()
    fun getDetails(idUser:Int): LiveData<Resource<List<UserModelDetail>>> {
      return  getUserDetailsUseCase(idUser).asLiveData()


    }
    fun getDetails1(idUser:Int){
        getUserDetailsUseCase(0).onEach { result->
            when(result) {
                is Resource.Success -> {

                }
                is Resource.Error -> {

                }
                is Resource.Loading-> {

                }
            }

        }

    }
}