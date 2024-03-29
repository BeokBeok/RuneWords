package com.beok.runewords.detail.data.model

import com.beok.runewords.common.ext.EMPTY
import com.beok.runewords.common.util.DataToDomainMapper
import com.beok.runewords.detail.domain.model.RuneWordsDetail

internal data class RuneWordsDetailResponse(
    val name: String = String.EMPTY,
    val rune_combination: List<String> = emptyList(),
    val type: List<String> = emptyList(),
    val option: String = String.EMPTY,
    val level_limit: Int = 1
) : DataToDomainMapper<RuneWordsDetail> {

    override fun toDomain(): RuneWordsDetail = RuneWordsDetail(
        name = name,
        runeCombination = rune_combination,
        type = type,
        option = option,
        levelLimit = level_limit
    )
}
