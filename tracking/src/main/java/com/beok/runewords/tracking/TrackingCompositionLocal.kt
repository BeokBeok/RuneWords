package com.beok.runewords.tracking

import androidx.compose.runtime.staticCompositionLocalOf

val LocalTracker = staticCompositionLocalOf<Tracking> {
    StubTracker()
}
