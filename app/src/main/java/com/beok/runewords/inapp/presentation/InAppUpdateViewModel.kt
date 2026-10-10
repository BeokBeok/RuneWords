package com.beok.runewords.inapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.inapp.domain.InAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class InAppUpdateViewModel @Inject constructor(
    private val inAppRepository: InAppRepository
) : ViewModel() {

    private val _event: MutableSharedFlow<InAppUpdateContract.Event> = MutableSharedFlow()

    private val _state: MutableStateFlow<InAppUpdateContract.State> =
        MutableStateFlow(InAppUpdateContract.State.Checking)
    val state: StateFlow<InAppUpdateContract.State> = _state.asStateFlow()

    private val _effect: Channel<InAppUpdateContract.Effect> = Channel()
    val effect: Flow<InAppUpdateContract.Effect> get() = _effect.receiveAsFlow()

    private var isChecked = false

    init {
        viewModelScope.launch {
            _event.collect(::handleEvent)
        }
    }

    fun handleEvent(event: InAppUpdateContract.Event) {
        when (event) {
            is InAppUpdateContract.Event.CheckInAppUpdateType -> {
                if (isChecked) return
                isChecked = true
                viewModelScope.launch {
                    inAppRepository.fetchForceUpdateVersion()
                        .onSuccess { forceUpdateVersion ->
                            if (forceUpdateVersion <= event.version) {
                                _state.value = InAppUpdateContract.State.Ready
                            } else {
                                _state.value = InAppUpdateContract.State.UpdateRequired
                                _effect.send(element = InAppUpdateContract.Effect.ForceUpdate)
                            }
                        }
                        .onFailure {
                            _state.value = InAppUpdateContract.State.Ready
                        }
                }
            }

            InAppUpdateContract.Event.UpdateUnavailable -> {
                _state.value = InAppUpdateContract.State.Ready
            }
        }
    }
}
