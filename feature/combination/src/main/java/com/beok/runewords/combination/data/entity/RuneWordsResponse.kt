package com.beok.runewords.combination.data.entity

import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.util.DataToDomainMapper

internal data class RuneWordsResponse(
    val name: String = String.EMPTY
): DataToDomainMapper<RuneWords> {

    override fun toDto(): RuneWords = RuneWords(
        name = name
    )
}
