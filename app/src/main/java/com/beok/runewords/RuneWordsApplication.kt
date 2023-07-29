package com.beok.runewords

import android.app.Application
import com.beok.runewords.manager.FlipperManager
import com.google.android.gms.ads.MobileAds
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.ktx.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
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

        Firebase.initialize(context = this)
        Firebase.appCheck.installAppCheckProviderFactory(
            if (BuildConfig.DEBUG) {
                DebugAppCheckProviderFactory.getInstance()
            } else {
                PlayIntegrityAppCheckProviderFactory.getInstance()
            }
        )
    }
}
