package com.supraweb.users.allusers_json.di

import android.app.Application
import androidx.room.Room
import com.supraweb.users.allusers_json.data.local.DatabaseUsersRoom
import com.supraweb.users.allusers_json.data.local.sharedpreference.UserSharePreference
import com.supraweb.users.allusers_json.data.remote.UserApi
import com.supraweb.users.allusers_json.data.repository.UserRepositoryDomainImpl
import com.supraweb.users.allusers_json.domain.repository.UserRepositoryDomain
import com.supraweb.users.allusers_json.domain.usecase.GetUsersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            // .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providerUserApiClient(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)

    }

    @Singleton
    @Provides
    fun providerUserUseCase(repository: UserRepositoryDomain): GetUsersListUseCase {
        return GetUsersListUseCase(repository)
    }

//
//    @Singleton
//    @Provides
//    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
//        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
//    }


    @Singleton
    @Provides
    fun providerUserRepositoryDomain(
        db: DatabaseUsersRoom,
        api: UserApi,
        pref: UserSharePreference
    ): UserRepositoryDomain {
        return UserRepositoryDomainImpl(api, db.getFromDataBaseUserDaoTable() ,pref)

    }

    @Provides
    @Singleton
    fun provideRoomDi(app: Application): DatabaseUsersRoom {
        return Room.databaseBuilder(
            app, DatabaseUsersRoom::class.java, QUOTE_DATABASE_NAME_clean
        )//.addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    private const val QUOTE_DATABASE_NAME_clean = "user_database_clean"

//    @Singleton
//    @Provides
//    fun provideRoomDi(@ApplicationContext context: Context) =
//        Room.databaseBuilder(context, DatabaseUsersRoom::class.java, QUOTE_DATABASE_NAME_clean).addTypeConverter(GsonParser(
//            Gson()
//        ))
//            .build()

    @Singleton
    @Provides
    fun provideUserDaoDi(db: DatabaseUsersRoom) = db.getFromDataBaseUserDaoTable()
/*
    private const val QUOTE_DATABASE_NAME = "user_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDataBase) = db.getUserDao()
 */

}

