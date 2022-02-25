package com.beok.runewords.combination.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RuneWordsFetchUseCaseImplTest {

    private val runeWordsRepository: RuneWordsRepository = RuneWordsRepository.Fake()
    private val useCase: RuneWordsFetchUseCase = RuneWordsFetchUseCase.Fake()

    @Test
    fun `룬에 해당하는 룬워드를 검색합니다`() = runBlocking {
        val rune = "el"
        val expected = runeWordsRepository.searchByRune(rune = rune)
        val actual = useCase
            .execute(rune = rune)
            .getOrNull()

        assertEquals(expected, actual)
    }
}
