package com.beok.runewords.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private val inAppUpdateViewModel by viewModels<InAppUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()
        observeInAppUpdate()
    }

    override fun onResume() {
        super.onResume()

        checkUpdatable()
    }

    private fun setupSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            setupScreenAd()
            false
        }
    }

    private fun setContent() {
        setContent {
            ActivityHomeView.Layout(
                context = this,
                runeClickTracking = { runeName ->
                    analytics.logEvent(
                        TrackingConstants.Rune.CLICK,
                        bundleOf(TrackingConstants.Params.RUNE_NAME to runeName)
                    )
                },
            )
        }
    }

    private fun checkUpdatable() {
        inAppUpdateViewModel.checkAppUpdatable()
    }

    private fun observeInAppUpdate() {
        inAppUpdateViewModel.state.observe(this) { state ->
            when (state) {
                InAppUpdateState.None,
                InAppUpdateState.Impossible -> Unit
                is InAppUpdateState.Possible -> {
                    inAppUpdateViewModel.registerForHome(
                        appUpdateInfo = state.info,
                        appUpdateType = AppUpdateType.IMMEDIATE,
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
            getString(R.string.admob_screen_app_key),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    setContent()
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    setContent()
                    ad.show(this@HomeActivity)
                }
            }
        )
    }
}
