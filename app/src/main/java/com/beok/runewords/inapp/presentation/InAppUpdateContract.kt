package com.beok.runewords.inapp.presentation

internal class InAppUpdateContract {

    sealed interface Event {
        data class CheckInAppUpdateType(val version: String) : Event
    }

    sealed interface Effect {
        data object ShowScreenAD : Effect
        data object ForceUpdate : Effect
    }
}
