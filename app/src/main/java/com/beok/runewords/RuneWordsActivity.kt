package com.beok.runewords

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.beok.runewords.home.BuildConfig
import com.beok.runewords.inapp.presentation.InAppUpdateContract
import com.beok.runewords.inapp.presentation.InAppUpdateViewModel
import com.beok.runewords.integrity.presentation.IntegrityContract
import com.beok.runewords.integrity.presentation.IntegrityViewModel
import com.beok.runewords.navigation.RuneWordsNavHost
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.Tracking
import com.beok.runewords.ui.RuneWordsTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.common.IntentSenderForResultStarter
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class RuneWordsActivity : ComponentActivity() {

    @Inject
    lateinit var tracking: Tracking

    @Inject
    lateinit var inAppUpdateManager: AppUpdateManager

    private val integrityViewModel by viewModels<IntegrityViewModel>()
    private val inAppUpdateViewModel by viewModels<InAppUpdateViewModel>()

    private val inAppUpdateLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.data == null) return@registerForActivityResult
        if (result.resultCode == REQ_IN_APP_UPDATE) {
            Toast.makeText(this, R.string.downloading, Toast.LENGTH_SHORT).show()
        }
        if (result.resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, R.string.downloading_failed, Toast.LENGTH_SHORT).show()
        }
    }

    private val updateResultStarter =
        IntentSenderForResultStarter { intent, _, fillInIntent, flagsMask, flagsValues, _, _ ->
            val request = IntentSenderRequest.Builder(intent)
                .setFillInIntent(fillInIntent)
                .setFlags(flagsValues, flagsMask)
                .build()
            inAppUpdateLauncher.launch(request)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        if (com.beok.runewords.BuildConfig.DEBUG) {
            refreshAppUpdateType()
        } else {
            checkIntegrity()
        }
        handleEffect()
    }

    private fun showAd() {
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

    private fun showContentWithAd() {
        setContent {
            RuneWordsTheme {
                LaunchedEffect(key1 = Unit) {
                    delay(500)
                    showAd()
                }
                CompositionLocalProvider(LocalTracker provides tracking) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        RuneWordsNavHost(showReviewWriteForm = ::showReviewWriteForm)
                    }
                }
            }
        }
    }

    private fun checkIntegrity() {
        integrityViewModel.handleEvent(
            event = IntegrityContract.Event.CheckIntegrity(
                requestHash = "aGVsbG8gd29scmQgdGhlcmU",
                gcpInputStream = assets.open("integrity.json")
            )
        )
    }

    private fun refreshAppUpdateType() {
        val packageManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L))
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }
        inAppUpdateViewModel.handleEvent(
            event = InAppUpdateContract.Event.CheckInAppUpdateType(
                version = packageManager.versionName.orEmpty()
            )
        )
    }

    private fun handleEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                integrityViewModel.effect.collect { effect ->
                    when (effect) {
                        IntegrityContract.Effect.Recognize -> {
                            refreshAppUpdateType()
                        }

                        is IntegrityContract.Effect.UnRecognize -> {
                            Firebase.crashlytics.recordException(effect.throwable)
                            refreshAppUpdateType()
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                inAppUpdateViewModel.effect.collect { effect ->
                    when (effect) {
                        InAppUpdateContract.Effect.ShowScreenAD -> {
                            showContentWithAd()
                        }

                        InAppUpdateContract.Effect.ForceUpdate -> {
                            forceUpdate()
                        }
                    }
                }
            }
        }
    }

    private fun forceUpdate() {
        inAppUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                when (appUpdateInfo.updateAvailability()) {
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS,
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        inAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            updateResultStarter,
                            AppUpdateOptions.defaultOptions(AppUpdateType.IMMEDIATE),
                            REQ_IN_APP_UPDATE
                        )
                    }

                    else -> {
                        showContentWithAd()
                    }
                }
            }
            .addOnFailureListener {
                showContentWithAd()
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

    companion object {
        private const val REQ_IN_APP_UPDATE = 755
    }
}
