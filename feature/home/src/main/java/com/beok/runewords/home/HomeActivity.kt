package com.beok.runewords.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.os.bundleOf
import com.beok.runewords.common.constants.TrackingConstants
import com.beok.runewords.home.inapp.InAppUpdateState
import com.beok.runewords.home.inapp.InAppUpdateViewModel
import com.google.android.gms.ads.MobileAds
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.InstallStatus
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: FirebaseAnalytics

    private val inAppUpdateViewModel by viewModels<InAppUpdateViewModel>()

    private val installStateUpdatedListener by lazy {
        InstallStateUpdatedListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                analytics.logEvent(TrackingConstants.InAppUpdate.DOWNLOADED, bundleOf())
                inAppUpdateViewModel.completeDownload()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
        setContent {
            ActivityHomeView.Layout(
                context = this,
                runeClickTracking = { runeName ->
                    analytics.logEvent(
                        TrackingConstants.Rune.CLICK,
                        bundleOf(TrackingConstants.Params.RUNE_NAME to runeName)
                    )
                },
                inAppUpdateState = inAppUpdateViewModel.state
                    .observeAsState(initial = InAppUpdateState.None)
                    .value,
                updateAction = inAppUpdateViewModel::installAndRestart
            )
        }
        setupListener()
        checkUpdatable()
        observeInAppUpdate()
    }

    override fun onDestroy() {
        inAppUpdateViewModel.unregisterInstallStateUpdatedListener(installStateUpdatedListener)
        super.onDestroy()
    }

    @Suppress("Deprecation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != InAppUpdateViewModel.REQ_IN_APP_UPDATE) return
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, getString(R.string.cancel_update), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun checkUpdatable() {
        inAppUpdateViewModel.checkAppUpdatable()
    }

    private fun setupListener() {
        inAppUpdateViewModel.registerInstallStateUpdatedListener(installStateUpdatedListener)
    }

    private fun observeInAppUpdate() {
        inAppUpdateViewModel.state.observe(this) { state ->
            when (state) {
                InAppUpdateState.None,
                InAppUpdateState.Downloaded -> Unit
                InAppUpdateState.Impossible -> {
                    inAppUpdateViewModel
                        .unregisterInstallStateUpdatedListener(installStateUpdatedListener)
                }
                is InAppUpdateState.Possible -> {
                    inAppUpdateViewModel.registerForHome(appUpdateInfo = state.info, target = this)
                }
                InAppUpdateState.Complete -> {
                    analytics.logEvent(TrackingConstants.InAppUpdate.INSTALL, bundleOf())
                    inAppUpdateViewModel.completeUpdate()
                    inAppUpdateViewModel
                        .unregisterInstallStateUpdatedListener(installStateUpdatedListener)
                }
            }.javaClass
        }
    }
}

