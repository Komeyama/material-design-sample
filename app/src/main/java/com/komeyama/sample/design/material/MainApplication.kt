package com.komeyama.sample.design.material

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Timber
        Timber.plant(Timber.DebugTree())
    }
}