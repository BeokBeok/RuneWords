package com.beok.runewords.detail.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RuneWordsDetailFetchUseCaseImplTest {

    private val repository: RuneWordsDetailRepository = RuneWordsDetailRepository.Fake()
    private val useCase: RuneWordsDetailFetchUseCase = RuneWordsDetailFetchUseCase.Fake()

    @Test
    fun `룬워드 정보를 불러옵니다`() = runBlocking {
        val runeWordsName = "call_to_arms"
        val expected = repository.fetchInfo(name = runeWordsName)
        val actual = useCase
            .execute(name = runeWordsName)
            .getOrNull()

        assertEquals(expected, actual)
    }
}
