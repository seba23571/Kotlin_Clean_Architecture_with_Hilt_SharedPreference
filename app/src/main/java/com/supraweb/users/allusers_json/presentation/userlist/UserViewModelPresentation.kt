package com.supraweb.users.allusers_json.presentation.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.supraweb.users.allusers_json.domain.usecase.GetUsersListUseCase
import com.supraweb.users.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModelPresentation        @Inject constructor (

private val repositoryUseCase: GetUsersListUseCase



        )                      : ViewModel()          {

    val uiState = MutableLiveData<UiState>()

    init {
        getUserResult()
    }

        val userList = repositoryUseCase.invoke().asLiveData()

    private fun getUserResult() {
        repositoryUseCase().onEach {      result ->
            when (result) {
                is Resource.Success -> {
                     uiState .value =  UiState.Success(result.data?: emptyList()   )
                 }
                is Resource.Error -> {

                    uiState .value =    UiState.Error(result.error?: "emptyList() ")
                }
                is Resource.Loading -> {

                    uiState .value = UiState.Loading




                }
            }
        }.launchIn(viewModelScope)
    }




}


