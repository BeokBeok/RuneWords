package com.beok.runewords.integrity.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppIntegrity(
    @SerialName("appRecognitionVerdict")
    val appRecognitionVerdict: AppRecognitionVerdict = AppRecognitionVerdict.NONE,
    @SerialName("certificateSha256Digest")
    val certificateSha256Digest: List<String> = emptyList(),
    @SerialName("packageName")
    val packageName: String = "",
    @SerialName("versionCode")
    val versionCode: String = ""
) {
    enum class AppRecognitionVerdict {
        PLAY_RECOGNIZED,
        UNRECOGNIZED_VERSION,
        UNEVALUATED,
        NONE;

        companion object {
            fun isRecognize(appRecognitionVerdict: AppRecognitionVerdict): Boolean {
                return appRecognitionVerdict == PLAY_RECOGNIZED
            }
        }
    }
}
