package com.beok.runewords.detail.data

import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RuneWordsDetailRepositoryImplTest {

    private val runeWordsDetailRemoteDataSource: RuneWordsDetailRemoteDataSource =
        RuneWordsDetailRemoteDataSource.Fake()
    private val repository: RuneWordsDetailRepository = RuneWordsDetailRepository.Fake()

    @Test
    fun `룬워드 정보를 불러옵니다`() = runBlocking {
        val runeWordsName = "call_to_arms"
        val expected = runeWordsDetailRemoteDataSource
            .fetchInfo(name = runeWordsName)
            .toDto()
            .copy(levelLimit = 57)
        val actual = repository.fetchInfo(name = runeWordsName)

        assertEquals(expected, actual)
    }
}
