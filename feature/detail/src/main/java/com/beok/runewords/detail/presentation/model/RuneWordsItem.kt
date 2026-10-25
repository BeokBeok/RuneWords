package com.beok.runewords.detail.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.model.Rune

@Stable
internal data class RuneWordsItem(
    val name: String = String.EMPTY,
    @StringRes val nameResourceID: Int = 0,
    val runeCombination: List<Rune> = emptyList(),
    val typeResourceIDs: List<Int> = emptyList(),
    @StringRes val optionResourceID: Int = 0,
    val levelLimit: Int = 1
) {

    fun isEmpty() = name.isEmpty() && runeCombination.isEmpty() && typeResourceIDs.isEmpty()
}
