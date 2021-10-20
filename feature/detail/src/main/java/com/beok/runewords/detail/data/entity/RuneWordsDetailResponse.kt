package com.beok.runewords.detail.data.entity

import com.beok.runewords.common.util.DataToDomainMapper
import com.beok.runewords.detail.domain.model.RuneWordsDetail

data class RuneWordsDetailResponse(
    val name: String = "",
    val rune_combination: List<String> = emptyList(),
    val type: List<String> = emptyList(),
) : DataToDomainMapper<RuneWordsDetail> {

    override fun toDto(): RuneWordsDetail = RuneWordsDetail(
        name = name,
        runeCombination = rune_combination,
        type = type,
    )
}
