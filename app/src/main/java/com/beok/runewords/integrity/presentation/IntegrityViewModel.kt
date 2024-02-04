package com.beok.runewords.integrity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.integrity.domain.IntegrityRepository
import com.beok.runewords.integrity.domain.model.AppRecognitionVerdict
import dagger.hilt.android.lifecycle.HiltViewModel
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
                    ).onSuccess { appRecognitionVerdict ->
                        _effect.send(
                            element = if (appRecognitionVerdict == AppRecognitionVerdict.NONE) {
                                IntegrityContract.Effect.Recognize
                            } else {
                                IntegrityContract.Effect.UnRecognize(
                                    throwable = IllegalStateException(
                                        "UnRecognize because ${appRecognitionVerdict.name}"
                                    )
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
