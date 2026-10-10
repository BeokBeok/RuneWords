package com.beok.runewords.inapp.presentation

internal class InAppUpdateContract {

    sealed interface State {
        data object Checking : State
        data object Ready : State
        data object UpdateRequired : State
    }

    sealed interface Event {
        data class CheckInAppUpdateType(val version: String) : Event
        data object UpdateUnavailable : Event
    }

    sealed interface Effect {
        data object ForceUpdate : Effect
    }
}
