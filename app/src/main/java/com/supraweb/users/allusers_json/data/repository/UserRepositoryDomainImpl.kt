package com.supraweb.users.allusers_json.data.repository

import android.util.Log
import com.supraweb.users.allusers_json.data.local.UsersDao
import com.supraweb.users.allusers_json.data.local.entity.UserEntityRoom
import com.supraweb.users.allusers_json.data.local.sharedpreference.UserSharePreference
import com.supraweb.users.allusers_json.data.remote.UserApi
import com.supraweb.users.allusers_json.data.remote.dto.UserDtoDetailRemote
import com.supraweb.users.allusers_json.domain.model.UserModelDomain
import com.supraweb.users.allusers_json.domain.repository.UserRepositoryDomain
import com.supraweb.users.core.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.joda.time.Hours
import org.joda.time.Instant
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

private const val TAG = "UserRepositoryDomainImp"
class UserRepositoryDomainImpl     @Inject constructor    (

    private val api :UserApi,
    private val dao : UsersDao,
    private val pref :UserSharePreference
) : UserRepositoryDomain {
    override fun getAllUserRepositoryDomai(): Flow<Resource<List<UserModelDomain>>> = flow {
       // emit(  Resource.Loading(true))

        emit(Resource.Loading          <           List<UserModelDomain>           >             ()              )
        val mapDomaddin = dao.getAllUsers().map { it.fromEntityToDomainLayer() }
        emit(        Resource.Success(mapDomaddin)                   )
        Resource.Loading(mapDomaddin.isNotEmpty())
        var usersRoom = dao.getAllUsers().map { it.fromEntityToDomainLayer() }
         try {
            val usersApiToEntity = api.getUsers().map{it.fromUsersDtoEntityToModelDomain()          }
             // comprueba pasada 6 horas renueva los datos del cache data ROOM SQLITE
               if (comprobarTiempo(usersApiToEntity))    {
                   Log.d(TAG, "getAllUserRepositoryDomai: Refresh Database")
               } else{
                   dao.insertUsers(usersApiToEntity)
               }

            // delay(5000)


         } catch (e: HttpException) {
            emit(Resource.Error(message = e.message.toString(),data=usersRoom )       )

        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString(),data=usersRoom )       )

        }
        val mapDomain = dao.getAllUsers().map { it.fromEntityToDomainLayer() }
        emit(        Resource.Success(mapDomain)                   )


       // emit(Resource.Loading(isLoading =true))
    }

    override suspend fun getPublish(userId: Int): List<UserDtoDetailRemote> {
      return  api.getPublish(userId)
    }

    suspend fun comprobarTiempo(usersApiToEntity: List<UserEntityRoom>): Boolean {  //abstract fun met(usersApiToEntity: List<UserEntityRoom>)
        val fechaNueva = System.currentTimeMillis()
        val dateNow: Instant = Instant.ofEpochMilli(fechaNueva)
        val lastSavedAt = pref.getLastSavedAt()// <string name="key_saved_at">2021-08-26T09:07:40.734-03:00</string>
        if (lastSavedAt == null) {
            pref.saveLastSavedAt(dateNow.toDateTime().toString()         )
            dao.deleteAllUsers()
            dao.insertUsers(usersApiToEntity?: emptyList() )
//            usersApiToEntity.isNotEmpty().also {     trueData->
//                Log.d(TAG, "comprobarTiempo: "+trueData)
//                dao.insertUsers(usersApiToEntity)
//
//            }
            return false
        }
        val dateSave = Instant.parse(lastSavedAt)
        if ( Hours.hoursBetween( dateSave    , dateNow ).getHours()   > 6    )       {
            pref.saveLastSavedAt(        dateNow.toDateTime().toString()              )
            dao.deleteAllUsers()
            dao.insertUsers(usersApiToEntity)

            return true
        }
        return false
    }
}


