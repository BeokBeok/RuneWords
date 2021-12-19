package com.beok.runewords.home.inapp

import com.google.android.play.core.appupdate.AppUpdateInfo

sealed class InAppUpdateState {

    object None : InAppUpdateState()

    object Impossible : InAppUpdateState()

    data class Possible(val info: AppUpdateInfo) : InAppUpdateState()

    object Complete : InAppUpdateState()

    object Downloaded : InAppUpdateState()
}
