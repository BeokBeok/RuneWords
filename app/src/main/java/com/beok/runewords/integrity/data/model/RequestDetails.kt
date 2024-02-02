package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDetails(
    @SerialName("requestHash")
    val requestHash: String = ""
)
