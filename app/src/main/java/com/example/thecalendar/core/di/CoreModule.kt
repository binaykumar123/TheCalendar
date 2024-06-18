package com.example.thecalendar.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.thecalendar.core.userdefault.SharedPreferenceHelper
import com.example.thecalendar.core.userdefault.UserDefault
import com.example.thecalendar.core.userservice.IUserService
import com.example.thecalendar.core.userservice.UserService
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    companion object {
        @Provides
        fun provideSharedPreference(
            @ApplicationContext context: Context,
            @Named("shared_preference_key") key: String
        ): SharedPreferences {
            return context.getSharedPreferences(key, Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        @Named("shared_preference_key")
        fun provideUserDefaultKey() = "calendar_app_user_preference"

        @Provides
        @Singleton
        fun provideGson(): Gson = Gson()

    }


    @Binds
    abstract fun provideUserDefault(sharedPreferenceHelper: SharedPreferenceHelper): UserDefault

    @Binds
    @Singleton
    abstract fun provideUserService(userService: UserService): IUserService
}