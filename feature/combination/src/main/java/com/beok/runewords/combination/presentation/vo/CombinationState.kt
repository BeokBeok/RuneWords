package com.beok.runewords.combination.presentation.vo

import com.beok.runewords.combination.domain.model.RuneWords

internal sealed class CombinationState {

    internal object None : CombinationState()

    internal data class Content(val value: List<RuneWords>) : CombinationState()

    internal object Loading : CombinationState()

    internal object Failed : CombinationState()
}
