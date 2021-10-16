package com.beok.runewords.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi

@SuppressLint("CustomSplashScreen")
class HomeActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActivityHomeView.Layout()
        }
    }
}
