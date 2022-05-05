package com.supraweb.users.allusers_json.data.remote.dto

import com.supraweb.users.allusers_json.domain.model.UserModelDetail

data class UserDtoDetailRemote (
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
        )

fun UserDtoDetailRemote.toUserModelDetail() : UserModelDetail {

    return  UserModelDetail(
        body =body,
        id=id,
        title=title,
        userId=userId
    )



}