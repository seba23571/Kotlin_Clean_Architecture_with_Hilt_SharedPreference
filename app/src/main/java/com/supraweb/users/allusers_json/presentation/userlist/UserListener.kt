package com.supraweb.users.allusers_json.presentation.userlist

import com.supraweb.users.allusers_json.domain.model.UserModelDomain

interface UserListener {
    fun doNavegation(currentUser: UserModelDomain)



}