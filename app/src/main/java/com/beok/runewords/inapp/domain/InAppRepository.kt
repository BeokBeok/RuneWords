package com.beok.runewords.inapp.domain

interface InAppRepository {

    suspend fun fetchForceUpdateVersion(): Result<String>
}
