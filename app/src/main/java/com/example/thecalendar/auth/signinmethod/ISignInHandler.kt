package com.example.thecalendar.auth.signinmethod

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

interface ISignInHandler {

    fun initAuth(fra: Activity)

    fun signIn(fragment: Fragment)

    fun onLoginActivityResult(data: Intent?): String?

}