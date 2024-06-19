package com.example.thecalendar.calendar.data

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential

interface IGoogleCredentialsHelper {
    fun getCredentials(): GoogleAccountCredential?
}