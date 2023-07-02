package com.beok.runewords.common.util

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

object HtmlConverter {

    fun fromTheme(source: String, isDarkTheme: Boolean): Spanned {
        val alphaCodeSize = 2
        val blackColorCode = Integer.toHexString(Color.Black.toArgb()).drop(alphaCodeSize)
        val whiteColorCode = Integer.toHexString(Color.White.toArgb()).drop(alphaCodeSize)
        val appliedThemeSource = if (isDarkTheme) {
            source.replace(oldValue = blackColorCode, newValue = whiteColorCode)
        } else {
            source
        }
        return Html.fromHtml(
            appliedThemeSource,
            Html.FROM_HTML_MODE_COMPACT
        )
    }
}
