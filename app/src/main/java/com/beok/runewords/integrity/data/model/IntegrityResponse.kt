package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntegrityResponse(
    @SerialName("tokenPayloadExternal")
    val tokenPayloadExternal: TokenPayloadExternal = TokenPayloadExternal(),
    @SerialName("error")
    val error: Error = Error()
)
