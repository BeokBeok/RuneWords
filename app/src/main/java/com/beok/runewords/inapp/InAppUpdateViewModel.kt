package com.beok.runewords.inapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.RuneWordsActivity
import com.beok.runewords.home.domain.FetchForceUpdateVersionUseCase
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class InAppUpdateViewModel @Inject constructor(
    private val inAppUpdateManager: AppUpdateManager,
    private val fetchForceUpdateVersionUseCase: FetchForceUpdateVersionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<InAppUpdateState>(InAppUpdateState.None)
    val state: LiveData<InAppUpdateState> get() = _state

    var appUpdateType: Int = AppUpdateType.FLEXIBLE
        private set

    fun refreshAppUpdateType(version: String) = viewModelScope.launch {
        fetchForceUpdateVersionUseCase.execute()
            .onSuccess { forceUpdateVersion ->
                if (forceUpdateVersion >= version) {
                    appUpdateType = AppUpdateType.IMMEDIATE
                }
            }
    }

    fun forceUpdate() {
        inAppUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                _state.value = when {
                    appUpdateInfo.updateAvailability() ==
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        InAppUpdateState.Possible(info = appUpdateInfo)
                    }
                    appUpdateInfo.updateAvailability() != UpdateAvailability.UPDATE_AVAILABLE -> {
                        InAppUpdateState.Impossible
                    }
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE) ||
                        appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE) -> {
                        InAppUpdateState.Possible(info = appUpdateInfo)
                    }
                    else -> InAppUpdateState.None
                }
            }
    }

    fun registerForHome(
        appUpdateInfo: AppUpdateInfo,
        target: RuneWordsActivity
    ) {
        inAppUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            appUpdateType,
            target,
            REQ_IN_APP_UPDATE
        )
    }

    fun completeUpdate() {
        inAppUpdateManager.completeUpdate()
    }

    companion object {
        const val REQ_IN_APP_UPDATE = 755
    }
}
