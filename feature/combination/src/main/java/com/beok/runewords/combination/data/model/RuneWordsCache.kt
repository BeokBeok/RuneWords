package com.beok.runewords.combination.data.model

import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.DataToDomainMapper
import kotlinx.serialization.Serializable

@Serializable
internal data class RuneWordsCache(val name: String, val mode: GameMode = GameMode.ALL) :
    DataToDomainMapper<RuneWords> {

    override fun toDomain(): RuneWords = RuneWords(name = name, mode = mode)

    companion object {
        fun from(runeWords: RuneWords): RuneWordsCache = RuneWordsCache(
            name = runeWords.name,
            mode = runeWords.mode
        )
    }
}
