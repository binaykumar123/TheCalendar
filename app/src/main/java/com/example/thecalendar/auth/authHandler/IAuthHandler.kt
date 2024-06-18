package com.example.thecalendar.auth.authHandler

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

interface IAuthHandler {
    fun initAuth(activity: Activity)

    fun signIn(fragment: Fragment)

    fun onLoginActivityResult(data: Intent?, activity: Activity)

    fun getAuthStatusLiveData(): LiveData<AuthStatus>
}