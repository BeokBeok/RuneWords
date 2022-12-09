package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.common.util.toDto
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class RuneWordsRepositoryImplTest {

    private val remoteDataSource: RuneWordsRemoteDataSource = RuneWordsRemoteDataSource.Fake()
    private val repository: RuneWordsRepository = RuneWordsRepository.Fake()

    @Test
    fun `룬에 해당하는 룬워드 아이템을 검색합니다`() = runBlocking {
        val rune = "el"
        val expected = remoteDataSource
            .searchByRune(rune = rune)
            .toDto()
        val actual = repository.searchByRune(rune = rune)

        assertEquals(expected, actual)
    }

    @Test
    fun `룬 정보 아이콘 타입을 얻습니다`() = runBlocking {
        val expected = remoteDataSource.fetchRuneInfoIconType()

        val actual = repository.fetchRuneInfoIconType()

        assertEquals(expected, actual)
    }
}
