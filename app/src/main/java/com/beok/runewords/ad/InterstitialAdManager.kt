package com.beok.runewords.ad

import android.app.Activity
import android.content.Context
import com.beok.runewords.BuildConfig
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class InterstitialAdManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val preferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
    private var ad: InterstitialAd? = null
    private var isLoading = false

    fun preload() {
        if (ad != null || isLoading || isShownToday()) return
        isLoading = true
        InterstitialAd.load(
            context,
            context.getString(
                if (BuildConfig.DEBUG) {
                    com.beok.runewords.common.R.string.test_admob_screen_app_key
                } else {
                    com.beok.runewords.common.R.string.admob_screen_app_key
                }
            ),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(loaded: InterstitialAd) {
                    ad = loaded
                    isLoading = false
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoading = false
                }
            }
        )
    }

    fun showIfAllowed(activity: Activity, onFinished: () -> Unit) {
        val current = ad
        if (current == null || isShownToday()) {
            preload()
            onFinished()
            return
        }
        current.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdShowedFullScreenContent() {
                preferences.edit()
                    .putLong(KEY_LAST_SHOWN_EPOCH_DAY, today())
                    .apply()
            }

            override fun onAdDismissedFullScreenContent() = onFinished()

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                preload()
                onFinished()
            }
        }
        ad = null
        current.show(activity)
    }

    private fun isShownToday(): Boolean =
        preferences.getLong(KEY_LAST_SHOWN_EPOCH_DAY, NEVER_SHOWN) == today()

    private fun today(): Long = LocalDate.now().toEpochDay()

    private companion object {
        private const val PREFERENCES_NAME = "interstitial_ad"
        private const val KEY_LAST_SHOWN_EPOCH_DAY = "last_shown_epoch_day"
        private const val NEVER_SHOWN = -1L
    }
}
