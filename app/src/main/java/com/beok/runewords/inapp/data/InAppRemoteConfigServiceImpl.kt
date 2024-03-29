package com.beok.runewords.inapp.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InAppRemoteConfigServiceImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : InAppRemoteConfigService {

    override suspend fun fetchForceUpdateVersion(): String = withContext(Dispatchers.IO) {
        remoteConfig.getString(KEY_FORCE_UPDATE_VERSION)
    }

    private companion object {
        private const val KEY_FORCE_UPDATE_VERSION = "key_force_update_version"
    }
}
