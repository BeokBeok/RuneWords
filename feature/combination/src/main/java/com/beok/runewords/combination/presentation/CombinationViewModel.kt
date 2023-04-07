package com.beok.runewords.combination.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.presentation.vo.CombinationState
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Result
import com.beok.runewords.common.model.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class CombinationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    runeWordsRepository: RuneWordsRepository,
) : ViewModel() {

    val rune: String by lazy {
        savedStateHandle[BundleKeyConstants.RUNE_NAME]!!
    }

    val combinationState: StateFlow<CombinationState> =
        runeWordsRepository.searchByRune(rune.lowercase())
            .asResult()
            .map { runeWordsResult ->
                when (runeWordsResult) {
                    is Result.Error -> CombinationState.Failed
                    Result.Loading -> CombinationState.Loading
                    is Result.Success -> CombinationState.Content(runeWordsResult.data)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombinationState.None
            )
}
