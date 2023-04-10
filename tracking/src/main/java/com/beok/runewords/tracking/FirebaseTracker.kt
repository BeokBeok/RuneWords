package com.beok.runewords.tracking

import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseTracker @Inject constructor() : Tracking {

    override fun logEvent(name: String, bundle: Bundle?) {
        Firebase.analytics.logEvent(name, bundle)
    }
}
