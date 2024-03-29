package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.common.util.toDomain
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first

internal class RuneWordsRepositoryImplTest {

    private val remoteDataSource: RuneWordsRemoteDataSource = RuneWordsRemoteDataSource.Fake()
    private val repository: RuneWordsRepository = RuneWordsRepository.Fake()

    @Test
    fun `룬에 해당하는 룬워드 아이템을 검색합니다`() = runBlocking {
        val rune = "zod"
        val expected = remoteDataSource
            .searchByRune(rune = rune)
            .toDomain()

        val actual = repository.searchByRune(rune = rune)
            .first()

        assertEquals(expected, actual)
    }
}
