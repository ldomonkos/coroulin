package hu.organicsoft.coroulin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class CoroulinApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
    }
}