package com.beok.runewords.combination.presentation.model

import com.beok.runewords.combination.domain.model.RuneWords

internal sealed class CombinationState {

    internal data object None : CombinationState()

    internal data class Content(val value: List<RuneWords>) : CombinationState()

    internal data object Loading : CombinationState()

    internal data object Failed : CombinationState()
}
