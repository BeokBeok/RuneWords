package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("code")
    val code: Int = Int.MIN_VALUE,
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: String = ""
)
