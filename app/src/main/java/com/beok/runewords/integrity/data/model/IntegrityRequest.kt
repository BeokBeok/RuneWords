package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntegrityRequest(
    @SerialName("integrity_token")
    val token: String
)
