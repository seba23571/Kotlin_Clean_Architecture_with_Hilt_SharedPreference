package com.supraweb.users.allusers_json.data.local.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.supraweb.users.allusers_json.domain.model.UserModelDomain


@Entity(tableName = "table_cleanarchitecture")

data class UserEntityRoom(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    //val address: Address,
    val phone: String,
    val website: String


)      {

    fun fromEntityToDomainLayer() : UserModelDomain {

        return UserModelDomain(

            id=id,
            name=name,
            username=username,
            email=email,
            phone=phone,
            website=website

        )
    }


}
