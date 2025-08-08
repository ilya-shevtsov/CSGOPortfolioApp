package com.example.csgocaseswatcherapp.core

import android.app.Application
import com.example.csgocaseswatcherapp.core.di.AppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import com.example.csgocaseswatcherapp.core.di.DaggerAppComponent

class CaseWatcherApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent() = appComponent
}