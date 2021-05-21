package com.example.dogexplorer

import android.app.Application
import com.example.dogexplorer.utils.DebugTree
import com.example.dogexplorer.utils.ProductionTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else ProductionTree())
    }
}