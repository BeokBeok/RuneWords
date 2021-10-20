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

    fun fetchDetail(name: String) = viewModelScope.launch {
        runeWordsDetailFetchUseCase
            .execute(name = name)
            .onSuccess {
                _detailInfo.value = RuneWordsVO.fromDto(it)
            }
            .onFailure(Timber::d)
    }
}
