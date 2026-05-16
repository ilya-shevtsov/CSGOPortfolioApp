package com.example.csgocaseswatcherapp

import android.app.Application
import com.skydoves.compose.stability.runtime.ComposeStabilityAnalyzer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp

class CaseWatcherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ComposeStabilityAnalyzer.setEnabled(BuildConfig.DEBUG)
    }

}