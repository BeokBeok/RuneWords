package com.beok.runewords.combination.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _runeWordsGroup = MutableLiveData<List<RuneWords>>()
    val runeWordsGroup: LiveData<List<RuneWords>> get() = _runeWordsGroup

    fun fetchRuneWords(rune: Rune?) = viewModelScope.launch {
        runeWordsFetchUseCase
            .execute(rune = rune?.name?.lowercase() ?: return@launch)
            .onSuccess {
                _runeWordsGroup.value = it
            }
            .onFailure(Timber::d)
    }
}
