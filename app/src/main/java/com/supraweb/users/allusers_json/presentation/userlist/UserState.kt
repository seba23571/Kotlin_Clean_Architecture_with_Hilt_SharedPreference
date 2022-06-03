package com.supraweb.users.allusers_json.presentation.userlist

import com.supraweb.users.allusers_json.domain.model.UserModelDomain

data class UserState(

      val Success: List<UserModelDomain> = emptyList(),
      val isLoading: Boolean = false,
    val error: String = ""

)

sealed class UserStateSealed {
    data class Success(val data: List<UserModelDomain>) : UserStateSealed()
    data class Error(val message: String) : UserStateSealed()
    object Loading : UserStateSealed()

}

sealed class UiState {
    data class Success(val data: List<UserModelDomain>) : UiState()
    data class Error(val message: String) : UiState()
    object Loading : UiState()
}
