package com.beok.runewords.integrity.presentation

import java.io.InputStream

class IntegrityContract {
    sealed interface Event {
        data class CheckIntegrity(
            val requestHash: String,
            val gcpInputStream: InputStream
        ) : Event
    }

    sealed interface Effect {
        data class UnRecognize(val throwable: Throwable) : Effect
        data object Recognize : Effect
    }
}
