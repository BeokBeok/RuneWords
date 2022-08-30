package com.beok.runewords.home.domain

interface HomeRepository {

    suspend fun fetchForceUpdateVersion(): String
}
