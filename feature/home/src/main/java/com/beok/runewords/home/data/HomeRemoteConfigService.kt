package com.beok.runewords.home.data

interface HomeRemoteConfigService {

    suspend fun fetchForceUpdateVersion(): String
}
