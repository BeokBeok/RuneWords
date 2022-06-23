package com.beok.runewords.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.presentation.vo.DetailState
import com.beok.runewords.detail.presentation.vo.RuneWordsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val runeWordsDetailFetchUseCase: RuneWordsDetailFetchUseCase
) : ViewModel() {

    private var _state by mutableStateOf<DetailState>(DetailState.None)
    val state: DetailState get() = _state

    fun fetchDetail(name: String) = viewModelScope.launch {
        _state = DetailState.Loading
        runeWordsDetailFetchUseCase
            .execute(name = name)
            .onSuccess {
                _state = DetailState.Content(value = RuneWordsVO.fromDto(it))
            }
            .onFailure {
                _state = DetailState.Failed
                Timber.d(it)
            }
    }
}
