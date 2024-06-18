package com.example.thecalendar.core.userservice

import com.example.thecalendar.core.userdefault.UserDefault
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserService @Inject constructor(
    private val userDefault: UserDefault,
    private val firebaseAuth: FirebaseAuth
) : IUserService {

    private val appUserKey = "app_user"
    override fun saveAppUser(appUser: AppUser) {
        userDefault.putObject(appUserKey, appUser)
    }

    override fun getSavedAppUser(): AppUser? {
        return userDefault.getObject(appUserKey, AppUser::class.java)
    }

    override fun isUserLoggedIn(): Boolean {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            userDefault.remove(appUserKey)
            return false
        }

        if (getSavedAppUser() == null) {
            return false
        }

        return true
    }
}