package com.example.thecalendar.calendar.data

import android.content.Context
import com.example.thecalendar.core.userservice.UserService
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.calendar.CalendarScopes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleAccountCredentialHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userService: UserService
) : IGoogleCredentialsHelper {
    private var credential: GoogleAccountCredential? = null

    override fun getCredentials(): GoogleAccountCredential? {
        if (credential == null) {
            initCredentials()
        }
        return credential
    }

    private fun initCredentials() {
        userService.getSavedAppUser()?.let { user ->
            credential =
                GoogleAccountCredential.usingOAuth2(
                    context,
                    arrayListOf(CalendarScopes.CALENDAR)
                ).setBackOff(ExponentialBackOff())
            credential?.selectedAccountName = user.email
        }
    }

}