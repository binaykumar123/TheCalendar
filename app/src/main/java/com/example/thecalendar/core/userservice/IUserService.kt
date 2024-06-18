package com.example.thecalendar.core.userservice

interface IUserService {

    fun saveAppUser(appUser: AppUser)
    fun getSavedAppUser(): AppUser?
    fun isUserLoggedIn(): Boolean

}