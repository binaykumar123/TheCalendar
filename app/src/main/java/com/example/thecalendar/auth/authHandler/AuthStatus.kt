package com.example.thecalendar.auth.authHandler

import com.example.thecalendar.core.userservice.AppUser

sealed interface AuthStatus {
    data class LoginSuccessful(val user: AppUser?) : AuthStatus
    data class LoginError(val msg: String?) : AuthStatus
}