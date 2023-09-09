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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.beok.runewords.home.BuildConfig
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
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class RuneWordsActivity : ComponentActivity() {

    @Inject
    lateinit var tracking: Tracking

    private val inAppUpdateViewModel by viewModels<InAppUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent()
        refreshAppUpdateType()
        observeInAppUpdate()
    }

    override fun onResume() {
        super.onResume()

        if (inAppUpdateViewModel.isForceUpdate()) {
            inAppUpdateViewModel.checkForceUpdate()
        }
    }

    private fun showScreenAd() {
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                delay(1_000)
                InterstitialAd.load(
                    this@RuneWordsActivity,
                    getString(
                        if (BuildConfig.DEBUG) {
                            com.beok.runewords.common.R.string.test_admob_screen_app_key
                        } else {
                            com.beok.runewords.common.R.string.admob_screen_app_key
                        }
                    ),
                    AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(loadAdError: LoadAdError) = Unit

                        override fun onAdLoaded(ad: InterstitialAd) {
                            ad.show(this@RuneWordsActivity)
                        }
                    }
                )
            }
        }
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
                InAppUpdateState.Impossible -> {
                    showScreenAd()
                }
                is InAppUpdateState.Possible -> {
                    inAppUpdateViewModel.requestInAppUpdate(
                        appUpdateInfo = state.info,
                        target = this
                    )
                }
                is InAppUpdateState.Error -> {
                    FirebaseCrashlytics.getInstance()
                        .recordException(state.throwable)
                    showScreenAd()
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
