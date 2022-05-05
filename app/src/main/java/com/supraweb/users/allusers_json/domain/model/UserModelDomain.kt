package com.supraweb.users.allusers_json.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UserModelDomain(

    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    //val address: Address,
    val phone: String,
    val website: String

): Serializable, Parcelable {

}
