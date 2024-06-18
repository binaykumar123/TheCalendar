package com.example.thecalendar.auth.di

import com.example.thecalendar.auth.authHandler.FirebaseAuthHandler
import com.example.thecalendar.auth.signinmethod.GoogleSignInHandler
import com.example.thecalendar.auth.authHandler.IAuthHandler
import com.example.thecalendar.auth.signinmethod.ISignInHandler
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthProviderModule {

    companion object {
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }


    @Binds
    @Singleton
    abstract fun provideAuthHandler(firebaseAuthHandler: FirebaseAuthHandler): IAuthHandler


    @Binds
    @Singleton
    abstract fun provideSignInHandler(googleSignInHandler: GoogleSignInHandler): ISignInHandler


}