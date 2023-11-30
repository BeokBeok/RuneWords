package com.beok.runewords.inapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.inapp.domain.InAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class InAppUpdateViewModel @Inject constructor(
    private val inAppRepository: InAppRepository
) : ViewModel() {

    private val _event: MutableSharedFlow<InAppUpdateContract.Event> = MutableSharedFlow()

    private val _effect: Channel<InAppUpdateContract.Effect> = Channel()
    val effect: Flow<InAppUpdateContract.Effect> get() = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _event.collect(::handleEvent)
        }
    }

    fun handleEvent(event: InAppUpdateContract.Event) {
        when (event) {
            is InAppUpdateContract.Event.CheckInAppUpdateType -> {
                viewModelScope.launch {
                    inAppRepository.fetchForceUpdateVersion()
                        .onSuccess { forceUpdateVersion ->
                            _effect.trySend(
                                element = if (forceUpdateVersion <= event.version) {
                                    InAppUpdateContract.Effect.ShowScreenAD
                                } else {
                                    InAppUpdateContract.Effect.ForceUpdate
                                }
                            )
                        }
                }
            }
        }
    }
}
