package com.beok.runewords.tracking

import android.os.Bundle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StubTracker @Inject constructor() : Tracking {

    override fun logEvent(name: String, bundle: Bundle?) = Unit
}
