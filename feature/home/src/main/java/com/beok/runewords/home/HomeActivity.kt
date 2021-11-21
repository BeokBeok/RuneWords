package com.beok.runewords.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.beok.runewords.common.constants.TrackingConstants
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: FirebaseAnalytics

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
                }
            )
        }
    }
}
