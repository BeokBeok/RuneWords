package com.beok.runewords.splash

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

object ActivitySplashView {

    @Composable
    fun Layout() {
        MaterialTheme {
            Surface {
                Text(text = "Hello World!")
            }
        }
    }
}
