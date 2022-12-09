package com.beok.runewords.combination.presentation

import com.beok.runewords.combination.domain.RuneInfoIconTypeFetchUseCase
import com.beok.runewords.combination.domain.RuneWordsFetchUseCase
import com.beok.runewords.combination.presentation.vo.CombinationState
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.test.MainCoroutineTestExtension
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class CombinationViewModelTest {

    private val useCase: RuneWordsFetchUseCase = RuneWordsFetchUseCase.Fake()
    private val runeInfoIconTypeFetchUseCase = RuneInfoIconTypeFetchUseCase.Fake()
    private lateinit var viewModel: CombinationViewModel

    @BeforeEach
    fun setup() {
        viewModel = CombinationViewModel(
            runeWordsFetchUseCase = useCase,
            runeInfoIconTypeFetchUseCase = runeInfoIconTypeFetchUseCase
        )
    }

    @Test
    fun `룬 기준으로 룬워드 리스트를_불러옵니다`() = runBlocking {
        // given
        val rune = Rune.BER
        val expected = CombinationState.Content(
            value = useCase.execute(rune.name.lowercase()).getOrNull() ?: emptyList()
        )

        // when
        viewModel.fetchRuneWords(rune)

        // then
        assertEquals(expected = expected, actual = viewModel.state)
    }

    @Test
    fun `룬 정보 아이콘 타입을_불러옵니다`() = runBlocking {
        // given
        val expected = runeInfoIconTypeFetchUseCase.execute()
            .getOrNull()

        // when
        viewModel.fetchRuneInfoIconType()

        // then
        assertEquals(expected = expected, actual = viewModel.runeInfoIconType)
    }
}
