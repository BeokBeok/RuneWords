package com.beok.runewords.info.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune

internal class RuneInfoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            (intent.extras?.get(BundleKeyConstants.RUNE_NAME) as? Rune)?.let {
                RuneInfoScreen(it)
            }
        }
    }
}
