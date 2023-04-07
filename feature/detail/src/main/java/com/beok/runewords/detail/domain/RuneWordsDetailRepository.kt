package com.beok.runewords.detail.domain

import com.beok.runewords.detail.domain.model.RuneWordsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface RuneWordsDetailRepository {

    fun fetchInfo(name: String): Flow<RuneWordsDetail>

    class Fake : RuneWordsDetailRepository {

        override fun fetchInfo(name: String): Flow<RuneWordsDetail> {
            return flow {
                emit(
                    RuneWordsDetail(
                        name = "call_to_arms",
                        runeCombination = listOf("amn", "ral", "mal", "ist", "ohm"),
                        type = listOf("all_weapons"),
                        option = "rune_word_call_to_arms_option",
                        levelLimit = 57
                    )
                )
            }
        }
    }
}
