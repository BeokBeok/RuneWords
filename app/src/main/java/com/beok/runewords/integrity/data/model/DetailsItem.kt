package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsItem(
    @SerialName("reason")
    val reason: String = "",
    @SerialName("metadata")
    val metadata: Metadata = Metadata(),
    @SerialName("type")
    val type: String = "",
    @SerialName("domain")
    val domain: String = ""
)
