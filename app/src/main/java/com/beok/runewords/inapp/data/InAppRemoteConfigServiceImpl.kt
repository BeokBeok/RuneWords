package com.beok.runewords.inapp.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber

class InAppRemoteConfigServiceImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : InAppRemoteConfigService {

    override suspend fun fetchForceUpdateVersion(): String = withContext(Dispatchers.IO) {
        try {
            withTimeoutOrNull(FETCH_TIMEOUT_MS) {
                remoteConfig.fetchAndActivate().await()
            }
        } catch (e: FirebaseRemoteConfigException) {
            Timber.w(e)
        }
        remoteConfig.getString(KEY_FORCE_UPDATE_VERSION)
    }

    private companion object {
        private const val KEY_FORCE_UPDATE_VERSION = "key_force_update_version"
        private const val FETCH_TIMEOUT_MS = 3_000L
    }
}
