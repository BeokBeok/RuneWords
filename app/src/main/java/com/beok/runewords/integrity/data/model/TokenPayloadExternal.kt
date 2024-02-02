package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenPayloadExternal(
    @SerialName("requestDetails")
    val requestDetails: RequestDetails = RequestDetails(),
    @SerialName("appIntegrity")
    val appIntegrity: AppIntegrity = AppIntegrity()
)
