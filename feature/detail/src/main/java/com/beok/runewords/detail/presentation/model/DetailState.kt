package com.beok.runewords.detail.presentation.model

internal sealed class DetailState {

    object None : DetailState()

    data class Content(val value: RuneWordsItem) : DetailState()

    object Loading : DetailState()

    object Failed : DetailState()
}
