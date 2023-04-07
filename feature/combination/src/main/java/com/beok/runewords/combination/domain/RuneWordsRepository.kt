package com.beok.runewords.combination.domain

import com.beok.runewords.combination.domain.model.RuneWords
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal interface RuneWordsRepository {

    fun searchByRune(rune: String): Flow<List<RuneWords>>

    class Fake : RuneWordsRepository {

        override fun searchByRune(rune: String): Flow<List<RuneWords>> {
            return flow {
                emit(
                    listOf(
                        RuneWords(name = "breath_of_the_dying"),
                        RuneWords(name = "obsession")
                    )
                )
            }
        }
    }
}
