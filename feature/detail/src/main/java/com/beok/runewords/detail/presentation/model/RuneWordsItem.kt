package com.beok.runewords.detail.presentation.model

import androidx.compose.runtime.Stable
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.model.Rune
import com.beok.runewords.detail.domain.model.RuneWordsDetail

@Stable
internal data class RuneWordsItem(
    val name: String = String.EMPTY,
    val runeCombination: List<Rune> = emptyList(),
    val type: List<String> = emptyList(),
    val option: String = String.EMPTY,
    val levelLimit: Int = 1
) {

    fun isEmpty() = name.isEmpty() && runeCombination.isEmpty() && type.isEmpty()

    companion object {

        fun fromDomain(runeWordsDetail: RuneWordsDetail): RuneWordsItem = RuneWordsItem(
            name = runeWordsDetail.name,
            runeCombination = runeWordsDetail.runeCombination.mapNotNull(Rune::findByName),
            type = runeWordsDetail.type,
            option = runeWordsDetail.option,
            levelLimit = runeWordsDetail.levelLimit
        )
    }
}
