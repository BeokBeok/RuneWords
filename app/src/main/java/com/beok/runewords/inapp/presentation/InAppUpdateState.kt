package com.beok.runewords.inapp.presentation

import com.google.android.play.core.appupdate.AppUpdateInfo

internal sealed class InAppUpdateState {

    object None : InAppUpdateState()

    object Impossible : InAppUpdateState()

    data class Possible(val info: AppUpdateInfo) : InAppUpdateState()

    data class Error(val throwable: Throwable) : InAppUpdateState()
}
