package com.supraweb.users.allusers_json.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.supraweb.users.allusers_json.data.local.entity.UserEntityRoom


@Database(
    entities = [  UserEntityRoom::class  ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DatabaseUsersRoom  : RoomDatabase() {

    abstract fun getFromDataBaseUserDaoTable():UsersDao
}