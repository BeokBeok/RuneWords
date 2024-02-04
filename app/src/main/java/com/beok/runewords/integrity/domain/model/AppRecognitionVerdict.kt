package com.beok.runewords.integrity.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class AppRecognitionVerdict {
    @SerialName("PLAY_RECOGNIZED")
    PLAY_RECOGNIZED,

    @SerialName("UNRECOGNIZED_VERSION")
    UNRECOGNIZED_VERSION,

    @SerialName("UNEVALUATED")
    UNEVALUATED,

    NOT_MATCH_HASH,
    NONE;
}
