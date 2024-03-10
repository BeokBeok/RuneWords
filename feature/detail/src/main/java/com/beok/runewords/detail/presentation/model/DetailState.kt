package com.beok.runewords.detail.presentation.model

internal sealed class DetailState {

    data object None : DetailState()

    data class Content(val value: RuneWordsItem) : DetailState()

    data object Loading : DetailState()

    data object Failed : DetailState()
}
