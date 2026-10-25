package com.beok.runewords.combination.presentation.model

internal sealed class CombinationState {

    internal data object None : CombinationState()

    internal data class Content(val value: List<RuneWordsItem>) : CombinationState()

    internal data object Loading : CombinationState()

    internal data object Failed : CombinationState()
}
