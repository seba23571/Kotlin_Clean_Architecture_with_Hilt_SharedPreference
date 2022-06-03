package com.supraweb.users.allusers_json.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.supraweb.users.allusers_json.data.local.entity.UserEntityRoom
 import kotlinx.coroutines.flow.Flow


@Dao
interface UsersDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userRoom: List<UserEntityRoom>)



    @Query("DELETE  FROM   table_cleanarchitecture")
    suspend fun deleteAllUsers()




    @Query("SELECT * FROm table_cleanarchitecture")
    suspend fun getAllUsers(): List<UserEntityRoom>



}