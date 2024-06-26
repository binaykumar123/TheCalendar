package com.example.thecalendar.auth.signinmethod

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.thecalendar.R
import com.example.thecalendar.core.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import javax.inject.Inject

class GoogleSignInHandler @Inject constructor(
    private val resources: Resources
) : ISignInHandler {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun initAuth(activity: Activity) {
        val signInOptions = createGoogleSignInOptions()
        googleSignInClient = GoogleSignIn.getClient(activity, signInOptions)
    }

    override fun signIn(fragment: Fragment) {
        val signInIntent = googleSignInClient.signInIntent
        fragment.startActivityForResult(signInIntent, Constants.RC_AUTH)
    }

    override fun onLoginActivityResult(data: Intent?): String? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            return account.idToken
        } catch (e: ApiException) {
            Log.e("TAG", "onLoginActivityResult: failed : ${e.message}")
        }
        return null
    }

    private fun createGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
}