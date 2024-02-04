package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    @SerialName("method")
    val method: String = "",
    @SerialName("service")
    val service: String = ""
)
