package com.beok.runewords.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.presentation.vo.RuneWordsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val runeWordsDetailFetchUseCase: RuneWordsDetailFetchUseCase
) : ViewModel() {

    private var _detailInfo by mutableStateOf(RuneWordsVO())
    val detailInfo: RuneWordsVO  get() = _detailInfo

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean get() = _isLoading

    fun fetchDetail(name: String) = viewModelScope.launch {
        _isLoading = true
        runeWordsDetailFetchUseCase
            .execute(name = name)
            .onSuccess {
                _isLoading = false
                _detailInfo = RuneWordsVO.fromDto(it)
            }
            .onFailure {
                _isLoading = false
                Timber.d(it)
            }
    }
}
