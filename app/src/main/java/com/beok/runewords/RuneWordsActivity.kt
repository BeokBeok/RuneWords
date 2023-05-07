package com.beok.runewords

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.beok.runewords.home.BuildConfig
import com.beok.runewords.home.R
import com.beok.runewords.inapp.presentation.InAppUpdateState
import com.beok.runewords.inapp.presentation.InAppUpdateViewModel
import com.beok.runewords.navigation.RuneWordsNavHost
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.Tracking
import com.beok.runewords.ui.RuneWordsTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class RuneWordsActivity : ComponentActivity() {

    @Inject
    lateinit var tracking: Tracking

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

        if (inAppUpdateViewModel.appUpdateType == AppUpdateType.IMMEDIATE) {
            inAppUpdateViewModel.checkForceUpdate()
        }
    }

    private fun setupScreenAd() {
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
                    ad.show(this@RuneWordsActivity)
                    setContent()
                }
            }
        )
    }

    private fun setContent() {
        setContent {
            RuneWordsTheme {
                CompositionLocalProvider(LocalTracker provides tracking) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        RuneWordsNavHost(showReviewWriteForm = ::showReviewWriteForm)
                    }
                }
            }
        }
    }

    private fun refreshAppUpdateType() {
        val packageManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0)
        }
        inAppUpdateViewModel.refreshAppUpdateType(version = packageManager.versionName)
    }

    private fun observeInAppUpdate() {
        inAppUpdateViewModel.state.observe(this) { state ->
            when (state) {
                InAppUpdateState.None,
                InAppUpdateState.Impossible -> Unit
                is InAppUpdateState.Possible -> {
                    inAppUpdateViewModel.requestInAppUpdate(
                        appUpdateInfo = state.info,
                        target = this
                    )
                }
                is InAppUpdateState.Error -> {
                    FirebaseCrashlytics.getInstance()
                        .recordException(state.throwable)
                }
            }
        }
    }

    private fun showReviewWriteForm() {
        val reviewManager = ReviewManagerFactory.create(this)
        reviewManager
            .requestReviewFlow()
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                reviewManager
                    .launchReviewFlow(this, it.result)
                    .addOnCompleteListener { }
            }
    }
}
