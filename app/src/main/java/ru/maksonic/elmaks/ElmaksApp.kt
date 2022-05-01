package ru.maksonic.elmaks

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author makosnic on 01.05.2022
 */
@HiltAndroidApp
class ElmaksApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}