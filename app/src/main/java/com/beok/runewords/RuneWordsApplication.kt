package com.beok.runewords

import android.app.Application
import com.beok.runewords.manager.FlipperManager
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RuneWordsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        if (BuildConfig.DEBUG) {
            FlipperManager(context = this).init()
        }
    }
}
