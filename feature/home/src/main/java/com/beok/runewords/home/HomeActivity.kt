package com.beok.runewords.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds

@SuppressLint("CustomSplashScreen")
internal class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
        setContent {
            ActivityHomeView.Layout(context = this)
        }
    }
}
