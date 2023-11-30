package com.example.list

import android.app.Application
import com.example.list.data.AppDatabase
import com.example.list.di.AppModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ListApplication: Application() {
}