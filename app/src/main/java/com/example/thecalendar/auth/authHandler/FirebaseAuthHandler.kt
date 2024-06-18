package com.example.thecalendar.auth.authHandler

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thecalendar.auth.signinmethod.ISignInHandler
import com.example.thecalendar.core.userservice.AppUser
import com.example.thecalendar.core.userservice.IUserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class FirebaseAuthHandler @Inject constructor(
    private val iSignInHandler: ISignInHandler,
    private val auth: FirebaseAuth,
    private val iUserService: IUserService
) : IAuthHandler {

    private val _authStatusLiveData = MutableLiveData<AuthStatus>()

    override fun initAuth(activity: Activity) {
        iSignInHandler.initAuth(activity)
    }

    override fun signIn(fragment: Fragment) {
        iSignInHandler.signIn(fragment)
    }

    override fun onLoginActivityResult(data: Intent?, activity: Activity) {
        val idToken = iSignInHandler.onLoginActivityResult(data)
        idToken?.let {
            firebaseAuthWithGoogle(idToken, activity)
        } ?: kotlin.run {
            _authStatusLiveData.postValue(AuthStatus.LoginError("invalid user"))
        }
    }

    override fun getAuthStatusLiveData(): LiveData<AuthStatus> {
        return _authStatusLiveData
    }


    private fun firebaseAuthWithGoogle(idToken: String, activity: Activity) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "firebaseAuthWithGoogle: $task successful")
                    onSignInSuccessful()
                } else {
                    Log.d("TAG", "firebaseAuthWithGoogle: $task failed")
                    _authStatusLiveData.postValue(AuthStatus.LoginError(task.exception?.message))
                }
            }
    }

    private fun onSignInSuccessful() {
        val firebaseUser = auth.currentUser
        val appUser = AppUser(
            firebaseUser?.displayName,
            firebaseUser?.email
        )
        iUserService.saveAppUser(
            appUser
        )
        _authStatusLiveData.postValue(AuthStatus.LoginSuccessful(appUser))
    }
}