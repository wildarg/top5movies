package com.example.top5movies

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Top5MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}