package com.beok.runewords.combination.presenter

import com.beok.runewords.combination.domain.RuneWordsFetchUseCase
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.common.model.Rune
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CombinationViewModelTest {

    private val runeWordsFetchUseCase: RuneWordsFetchUseCase = mockk(relaxed = true)
    private lateinit var viewModel: CombinationViewModel

    @BeforeEach
    fun setup() {
        viewModel = CombinationViewModel(runeWordsFetchUseCase = runeWordsFetchUseCase)
    }

    @Test
    fun `룬에 해당하는 룬워드를 검색합니다`() {
        val rune = Rune.EL
        val mockResponse: List<RuneWords> = listOf(mockk())
        coEvery {
            runeWordsFetchUseCase
                .execute(rune = rune.name.lowercase())
                .getOrNull()
        } returns mockResponse

        viewModel.fetchRuneWords(rune = rune)

        assertEquals(mockResponse, viewModel.runeWordsGroup)
    }
}
