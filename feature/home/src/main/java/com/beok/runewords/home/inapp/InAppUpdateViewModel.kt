package com.beok.runewords.home.inapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beok.runewords.home.HomeActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class InAppUpdateViewModel @Inject constructor(
    private val inAppUpdateManager: AppUpdateManager
) : ViewModel() {

    private var _state = MutableLiveData<InAppUpdateState>(InAppUpdateState.None)
    val state: LiveData<InAppUpdateState> get() = _state

    fun checkAppUpdatable() {
        inAppUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                when {
                    appUpdateInfo.updateAvailability() != UpdateAvailability.UPDATE_AVAILABLE -> {
                        _state.value = InAppUpdateState.Impossible
                    }
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE) -> {
                        _state.value = InAppUpdateState.Possible(info = appUpdateInfo)
                    }
                }
            }
    }

    fun registerForHome(
        appUpdateInfo: AppUpdateInfo,
        appUpdateType: Int = AppUpdateType.FLEXIBLE,
        target: HomeActivity
    ) {
        inAppUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            appUpdateType,
            target,
            REQ_IN_APP_UPDATE
        )
    }

    fun registerInstallStateUpdatedListener(listener: InstallStateUpdatedListener) {
        inAppUpdateManager.registerListener(listener)
    }

    fun unregisterInstallStateUpdatedListener(listener: InstallStateUpdatedListener) {
        inAppUpdateManager.unregisterListener(listener)
    }

    fun completeDownload() {
        _state.value = InAppUpdateState.Downloaded
    }

    fun installAndRestart() {
        _state.value = InAppUpdateState.Complete
    }

    fun completeUpdate() {
        inAppUpdateManager.completeUpdate()
    }

    companion object {
        const val REQ_IN_APP_UPDATE = 755
    }
}