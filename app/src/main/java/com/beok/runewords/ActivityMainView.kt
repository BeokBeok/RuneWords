package com.beok.runewords

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

object ActivityMainView {

    @Composable
    fun Layout() {
        MaterialTheme {
            Surface {
                Text(text = "Hello World!")
            }
        }
    }
}
