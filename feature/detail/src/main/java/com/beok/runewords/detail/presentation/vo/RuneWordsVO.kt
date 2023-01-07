package com.beok.runewords.detail.presentation.vo

import androidx.compose.runtime.Stable
import com.beok.runewords.common.model.Rune
import com.beok.runewords.detail.domain.model.RuneWordsDetail

@Stable
internal data class RuneWordsVO(
    val name: String = "",
    val runeCombination: List<Rune> = emptyList(),
    val type: List<String> = emptyList(),
    val option: String = "",
    val levelLimit: Int = 1
) {

    fun isEmpty() = name.isEmpty() && runeCombination.isEmpty() && type.isEmpty()

    companion object {

        fun fromDto(dto: RuneWordsDetail): RuneWordsVO = RuneWordsVO(
            name = dto.name,
            runeCombination = dto.runeCombination.mapNotNull(Rune::findByName),
            type = dto.type,
            option = dto.option,
            levelLimit = dto.levelLimit
        )
    }
}
