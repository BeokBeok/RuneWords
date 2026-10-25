package com.beok.runewords.combination.data.model

import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.util.DataToDomainMapper

internal data class RuneWordsResponse(val name: String = String.EMPTY, val mode: String? = null) :
    DataToDomainMapper<RuneWords> {

    override fun toDomain(): RuneWords = RuneWords(
        name = name,
        mode = when (mode) {
            MODE_STANDARD -> GameMode.STANDARD
            MODE_LADDER -> GameMode.LADDER
            else -> GameMode.ALL
        }
    )

    companion object {
        private const val MODE_STANDARD = "standard"
        private const val MODE_LADDER = "ladder"
    }
}
