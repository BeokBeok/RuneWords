package com.beok.runewords.combination.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.combination.domain.RuneInfoIconTypeFetchUseCase
import com.beok.runewords.combination.domain.RuneWordsFetchUseCase
import com.beok.runewords.combination.presentation.vo.CombinationState
import com.beok.runewords.common.model.Rune
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
internal class CombinationViewModel @Inject constructor(
    private val runeWordsFetchUseCase: RuneWordsFetchUseCase,
    private val runeInfoIconTypeFetchUseCase: RuneInfoIconTypeFetchUseCase
) : ViewModel() {

    private var _state by mutableStateOf<CombinationState>(CombinationState.None)
    val state: CombinationState get() = _state

    private var _runeInfoIconType by mutableStateOf("")
    val runeInfoIconType: String get() = _runeInfoIconType

    fun fetchRuneWords(rune: Rune?) = viewModelScope.launch {
        _state = CombinationState.Loading
        runeWordsFetchUseCase
            .execute(rune = rune?.name?.lowercase() ?: return@launch)
            .onSuccess {
                _state = CombinationState.Content(value = it)
            }
            .onFailure {
                _state = CombinationState.Failed
                Timber.d(it)
            }
    }

    fun fetchRuneInfoIconType() = viewModelScope.launch {
        runeInfoIconTypeFetchUseCase.execute()
            .onSuccess {
                _runeInfoIconType = it
            }
    }
}
