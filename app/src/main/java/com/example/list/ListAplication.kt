package com.example.list

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ListApplication: Application() {
    companion object {
        var authStateListener: FirebaseAuth.AuthStateListener? = null
    }
}