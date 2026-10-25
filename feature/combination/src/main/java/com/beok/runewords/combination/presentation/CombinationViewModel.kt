package com.beok.runewords.combination.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.presentation.mapper.RuneWordsItemMapper
import com.beok.runewords.combination.presentation.model.CombinationState
import com.beok.runewords.combination.presentation.navigation.Combination
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
    runeWordsItemMapper: RuneWordsItemMapper
) : ViewModel() {

    val rune: String = savedStateHandle.toRoute<Combination>().rune

    val combinationState: StateFlow<CombinationState> =
        runeWordsRepository.searchByRune(rune.lowercase())
            .asResult()
            .map { runeWordsResult ->
                when (runeWordsResult) {
                    is Result.Error -> CombinationState.Failed
                    Result.Loading -> CombinationState.Loading
                    is Result.Success -> CombinationState.Content(
                        runeWordsItemMapper.toItems(runeWordsResult.data)
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CombinationState.None
            )
}
