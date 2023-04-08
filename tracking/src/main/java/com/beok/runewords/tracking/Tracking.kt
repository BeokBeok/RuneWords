package com.beok.runewords.tracking

import android.os.Bundle

interface Tracking {

    fun logEvent(name: String, bundle: Bundle? = null)
}
