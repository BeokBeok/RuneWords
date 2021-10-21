package com.beok.runewords.detail.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.presenter.vo.RuneWordsVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val runeWordsDetailFetchUseCase: RuneWordsDetailFetchUseCase
) : ViewModel() {

    private val _detailInfo = MutableLiveData<RuneWordsVO>()
    val detailInfo: LiveData<RuneWordsVO> get() = _detailInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchDetail(name: String) = viewModelScope.launch {
        _isLoading.value = true
        runeWordsDetailFetchUseCase
            .execute(name = name)
            .onSuccess {
                _isLoading.value = false
                _detailInfo.value = RuneWordsVO.fromDto(it)
            }
            .onFailure {
                _isLoading.value = false
                Timber.d(it)
            }
    }
}
