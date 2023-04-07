package com.beok.runewords.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Result
import com.beok.runewords.common.model.asResult
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.presentation.vo.DetailState
import com.beok.runewords.detail.presentation.vo.RuneWordsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    runeWordsDetailRepository: RuneWordsDetailRepository,
) : ViewModel() {

    private val runeWordsName: String by lazy {
        savedStateHandle[BundleKeyConstants.RUNE_WORDS_NAME]!!
    }

    val detailState: StateFlow<DetailState> =
        runeWordsDetailRepository.fetchInfo(runeWordsName)
            .asResult()
            .map { detailResult ->
                when (detailResult) {
                    is Result.Error -> DetailState.Failed
                    Result.Loading -> DetailState.Loading
                    is Result.Success -> DetailState.Content(RuneWordsVO.fromDto(detailResult.data))
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DetailState.None
            )
}
