package com.beok.runewords.integrity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.integrity.domain.IntegrityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.RuntimeException
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class IntegrityViewModel @Inject constructor(
    private val integrityRepository: IntegrityRepository
) : ViewModel() {
    private val _event: MutableSharedFlow<IntegrityContract.Event> = MutableSharedFlow()
    private val _effect: Channel<IntegrityContract.Effect> = Channel()
    val effect: Flow<IntegrityContract.Effect> get() = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _event.collect(::handleEvent)
        }
    }

    fun handleEvent(event: IntegrityContract.Event) {
        when (event) {
            is IntegrityContract.Event.CheckIntegrity -> {
                viewModelScope.launch {
                    integrityRepository.integrity(
                        requestHash = event.requestHash,
                        gcpInputStream = event.gcpInputStream
                    ).onSuccess { isRecognize ->
                        _effect.send(
                            element = if (isRecognize) {
                                IntegrityContract.Effect.Recognize
                            } else {
                                IntegrityContract.Effect.UnRecognize(
                                    throwable = RuntimeException("recognize failed")
                                )
                            }
                        )
                    }.onFailure {
                        _effect.send(
                            element = IntegrityContract.Effect.UnRecognize(throwable = it)
                        )
                    }
                }
            }
        }
    }
}
