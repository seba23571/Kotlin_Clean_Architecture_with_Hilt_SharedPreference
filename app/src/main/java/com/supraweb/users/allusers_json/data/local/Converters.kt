package com.supraweb.users.allusers_json.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.supraweb.users.allusers_json.data.util.JsonParser
import com.supraweb.users.allusers_json.domain.model.UserModelDomain

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromUsersJson(json: String): List<UserModelDomain> {
        return jsonParser.fromJson<ArrayList<UserModelDomain>>(
            json,
            object : TypeToken<ArrayList<UserModelDomain>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toUsersJson(userList: List<UserModelDomain>): String {
        return jsonParser.toJson(
            userList,
            object : TypeToken<ArrayList<UserModelDomain>>(){}.type
        ) ?: "[]"
    }
}