package com.supraweb.users.allusers_json.di

import android.app.Application
import android.util.Log
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: IInterceptorModule, logger: LoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor( httpLoggingInterceptor)
            .addInterceptor(logger.loggger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            // .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .baseUrl(BASE_URL)
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



    @Singleton
    @Provides
    fun provideSomeString(app: Application) : IInterceptorModule { //     fun provideSomeString(@ApplicationContext appContext: Context) : IFacturaModule {
       // Log.d(TAG, "provideSomeString: provideSomeString" +app.hashCode())
        return  InterceptorModule(app)
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

