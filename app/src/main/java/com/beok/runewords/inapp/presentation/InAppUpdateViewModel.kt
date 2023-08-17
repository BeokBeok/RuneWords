package com.beok.runewords.inapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.RuneWordsActivity
import com.beok.runewords.inapp.domain.InAppRepository
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class InAppUpdateViewModel @Inject constructor(
    private val inAppUpdateManager: AppUpdateManager,
    private val inAppRepository: InAppRepository
) : ViewModel() {

    private val _state = MutableLiveData<InAppUpdateState>()
    val state: LiveData<InAppUpdateState> get() = _state

    private var appUpdateType: Int = AppUpdateType.FLEXIBLE

    fun refreshAppUpdateType(version: String) = viewModelScope.launch {
        inAppRepository.fetchForceUpdateVersion()
            .onSuccess { forceUpdateVersion ->
                if (forceUpdateVersion > version) {
                    appUpdateType = AppUpdateType.IMMEDIATE
                    return@onSuccess
                }
                checkForceUpdate()
            }
    }

    fun checkForceUpdate() {
        inAppUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                _state.value = when (appUpdateInfo.updateAvailability()) {
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS,
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        InAppUpdateState.Possible(info = appUpdateInfo)
                    }

                    else -> InAppUpdateState.Impossible
                }
            }
            .addOnFailureListener {
                _state.value = InAppUpdateState.None
            }
    }

    fun requestInAppUpdate(
        appUpdateInfo: AppUpdateInfo,
        target: RuneWordsActivity
    ) {
        if (isForceUpdate().not()) return
        runCatching {
            inAppUpdateManager.startUpdateFlowForResult(
                appUpdateInfo,
                appUpdateType,
                target,
                REQ_IN_APP_UPDATE
            )
        }.onFailure {
            _state.value = InAppUpdateState.Error(it)
        }
    }

    fun isForceUpdate(): Boolean {
        return appUpdateType == AppUpdateType.IMMEDIATE
    }

    companion object {
        const val REQ_IN_APP_UPDATE = 755
    }
}
