package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.local.RuneDAO
import com.beok.runewords.combination.data.model.RuneTable
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.util.toDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class RuneWordsRepositoryImpl @Inject constructor(
    private val localDataSource: RuneDAO,
    private val remoteDataSource: RuneWordsRemoteDataSource
) : RuneWordsRepository {

    override fun searchByRune(rune: String): Flow<List<RuneWords>> {
        return localDataSource.findRuneWordsBy(rune)
            .flatMapConcat { runeWords ->
                if (runeWords.isNotEmpty()) {
                    return@flatMapConcat flowOf(
                        runeWords.flatMap { runeTable ->
                            runeTable.runewords.map(::RuneWords)
                        }
                    )
                }
                flowOf(
                    remoteDataSource.searchByRune(rune)
                        .toDomain()
                        .also {
                            localDataSource.insert(
                                RuneTable(name = rune, runewords = it.map(RuneWords::name))
                            )
                        }
                )
            }
    }
}
