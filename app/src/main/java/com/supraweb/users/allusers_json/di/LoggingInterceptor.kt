package com.supraweb.users.allusers_json.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggingInterceptor @Inject constructor(@ApplicationContext context: Context)  {
    private val applicationContext = context.applicationContext
      val loggger: HttpLoggingInterceptor =            HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)


}