package com.beok.runewords.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

object ActivityHomeView {

    @Composable
    fun Layout() {
        MaterialTheme {
            Surface {
                Text(text = "Hello World!")
            }
        }
    }
}
