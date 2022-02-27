package com.beok.runewords.detail.presenter.vo

import com.beok.runewords.common.model.Rune
import com.beok.runewords.detail.domain.model.RuneWordsDetail

internal data class RuneWordsVO(
    val name: String = "",
    val runeCombination: List<Rune> = emptyList(),
    val type: List<String> = emptyList(),
    val option: String = ""
) {

    fun isEmpty() = name.isEmpty() && runeCombination.isEmpty() && type.isEmpty()

    companion object {

        fun fromDto(dto: RuneWordsDetail): RuneWordsVO = RuneWordsVO(
            name = dto.name,
            runeCombination = dto.runeCombination.mapNotNull(Rune::findByName),
            type = dto.type,
            option = dto.option
        )
    }
}
