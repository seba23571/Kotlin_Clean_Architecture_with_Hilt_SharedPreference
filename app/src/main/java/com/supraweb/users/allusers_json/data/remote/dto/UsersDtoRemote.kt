package com.supraweb.users.allusers_json.data.remote.dto

import com.supraweb.users.allusers_json.data.local.entity.UserEntityRoom
import com.supraweb.users.allusers_json.domain.model.UserModelDomain

data class UsersDtoRemote
    (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    //val address: Address,
    val phone: String,
    val website: String
    // val company: Company
)

{
        fun fromUsersDtoRemoteToModelDomain() :UserModelDomain {

            return UserModelDomain(

                id=id,
                name=name,
                username=username,
                email=email,
                phone=phone,
                website=website

            )
        }
    fun fromUsersDtoEntityToModelDomain() :UserEntityRoom {

        return UserEntityRoom(

            id=id,
            name=name,
            username=username,
            email=email,
            phone=phone,
            website=website

        )

    }




    }