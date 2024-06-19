package com.example.thecalendar.login.ui.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.thecalendar.auth.authHandler.AuthStatus
import com.example.thecalendar.auth.authHandler.IAuthHandler
import com.example.thecalendar.core.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val iAuthHandler: IAuthHandler
) : ViewModel() {


    private var _authStatusLiveData = MediatorLiveData<AuthStatus>()
    val authStatusLiveData: LiveData<AuthStatus>
        get() = _authStatusLiveData

    fun initAuthHandler(activity: Activity): LiveData<AuthStatus> {
        iAuthHandler.initAuth(activity)
        observeAuthStatus()
        return authStatusLiveData
    }

    private fun observeAuthStatus() {
        _authStatusLiveData.addSource(iAuthHandler.getAuthStatusLiveData()) {
            _authStatusLiveData.postValue(it)
        }
    }

    fun signIn(fragment: Fragment) {
        iAuthHandler.signIn(fragment)
    }

    fun onLoginActivityResult(
        requestCode: Int,
        data: Intent?,
        activity: Activity
    ) {
        if (requestCode == Constants.RC_AUTH) {
            iAuthHandler.onLoginActivityResult(data, activity)
        }
    }

}