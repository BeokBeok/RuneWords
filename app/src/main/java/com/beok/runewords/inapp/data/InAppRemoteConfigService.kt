package com.beok.runewords.inapp.data

interface InAppRemoteConfigService {

    suspend fun fetchForceUpdateVersion(): String
}
