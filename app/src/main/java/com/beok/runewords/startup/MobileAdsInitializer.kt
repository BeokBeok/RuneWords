package com.beok.runewords.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.android.gms.ads.MobileAds

object MobileAdsInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        MobileAds.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
