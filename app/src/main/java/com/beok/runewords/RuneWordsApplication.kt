package com.beok.runewords

import android.app.Application
import timber.log.Timber

class RuneWordsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant()
        }
    }
}
