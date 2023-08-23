package ru.z3rg.footballtoday

import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import dagger.hilt.android.HiltAndroidApp

const val ONESIGNAL_APP_ID = "84b704ce-4320-4883-9178-d34dc9cfa62e"

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
        OneSignal.User.pushSubscription

    }
}