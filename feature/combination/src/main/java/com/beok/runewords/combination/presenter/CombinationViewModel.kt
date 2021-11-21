package com.beok.runewords.combination.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.combination.domain.RuneWordsFetchUseCase
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.model.Rune
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
internal class CombinationViewModel @Inject constructor(
    private val runeWordsFetchUseCase: RuneWordsFetchUseCase
): ViewModel() {

    var runeWordsGroup = mutableStateListOf<RuneWords>()
        private set

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean get() = _isLoading

    fun fetchRuneWords(rune: Rune?) = viewModelScope.launch {
        _isLoading = true
        runeWordsFetchUseCase
            .execute(rune = rune?.name?.lowercase() ?: return@launch)
            .onSuccess {
                _isLoading = false
                runeWordsGroup.clear()
                runeWordsGroup.addAll(it)
            }
            .onFailure {
                _isLoading = false
                Timber.d(it)
            }
    }
}
