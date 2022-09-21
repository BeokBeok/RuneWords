package com.beok.runewords.detail.presentation.vo

internal sealed class DetailState {

    object None : DetailState()

    data class Content(val value: RuneWordsVO) : DetailState()

    object Loading : DetailState()

    object Failed : DetailState()
}
