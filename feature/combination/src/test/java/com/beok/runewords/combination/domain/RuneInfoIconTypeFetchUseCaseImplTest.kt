package com.beok.runewords.combination.domain

import org.junit.jupiter.api.Assertions.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class RuneInfoIconTypeFetchUseCaseImplTest {

    private val repository: RuneWordsRepository = RuneWordsRepository.Fake()
    private val useCase: RuneInfoIconTypeFetchUseCase = RuneInfoIconTypeFetchUseCase.Fake()

    @Test
    fun `룬 정보 아이콘 타입을 불러옵니다`() = runBlocking {
        val expected = repository.fetchRuneInfoIconType()

        val actual = useCase.execute()
            .getOrNull()

        assertEquals(expected, actual)
    }
}
