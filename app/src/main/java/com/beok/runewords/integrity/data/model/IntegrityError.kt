package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntegrityError(
    @SerialName("code")
    val code: Int = Int.MIN_VALUE,
    @SerialName("details")
    val details: List<DetailsItem> = emptyList(),
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: String = ""
)
