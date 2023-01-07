package com.beok.runewords.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.beok.runewords.common.constants.TrackingConstants
import com.beok.runewords.home.inapp.InAppUpdateState
import com.beok.runewords.home.inapp.InAppUpdateViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.play.core.install.model.AppUpdateType
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private val inAppUpdateViewModel by viewModels<InAppUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setupScreenAd()
        refreshAppUpdateType()
        observeInAppUpdate()
    }

    override fun onResume() {
        super.onResume()

        if (inAppUpdateViewModel.appUpdateType == AppUpdateType.IMMEDIATE) forceUpdate()
    }

    private fun setContent() {
        setContent {
            ActivityHomeView.Layout(
                runeClickTracking = { runeName ->
                    analytics.logEvent(
                        TrackingConstants.Rune.CLICK,
                        bundleOf(TrackingConstants.Params.RUNE_NAME to runeName)
                    )
                },
            )
        }
    }

    private fun refreshAppUpdateType() {
        inAppUpdateViewModel.refreshAppUpdateType(
            version = packageManager.getPackageInfo(packageName, 0).versionName
        )
    }

    private fun forceUpdate() {
        inAppUpdateViewModel.forceUpdate()
    }

    private fun observeInAppUpdate() {
        inAppUpdateViewModel.state.observe(this) { state ->
            when (state) {
                InAppUpdateState.None,
                InAppUpdateState.Impossible -> Unit
                is InAppUpdateState.Possible -> {
                    inAppUpdateViewModel.registerForHome(
                        appUpdateInfo = state.info,
                        target = this
                    )
                }
            }.javaClass
        }
    }

    private fun setupScreenAd() {
        MobileAds.initialize(this)
        InterstitialAd.load(
            this,
            getString(
                if (BuildConfig.DEBUG) {
                    R.string.test_admob_screen_app_key
                } else {
                    R.string.admob_screen_app_key
                }
            ),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    setContent()
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    ad.show(this@HomeActivity)
                    setContent()
                }
            }
        )
    }
}
