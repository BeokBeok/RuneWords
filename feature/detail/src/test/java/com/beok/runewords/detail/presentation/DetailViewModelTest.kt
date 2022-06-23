package com.beok.runewords.detail.presentation

import com.beok.runewords.common.test.MainCoroutineTestExtension
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.presentation.vo.DetailState
import com.beok.runewords.detail.presentation.vo.RuneWordsVO
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class DetailViewModelTest {

    private val useCase: RuneWordsDetailFetchUseCase = RuneWordsDetailFetchUseCase.Fake()
    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setup() {
        viewModel = DetailViewModel(runeWordsDetailFetchUseCase = useCase)
    }

    @Test
    fun `룬워드 상세 정보를_불러옵니다`() = runBlocking {
        // given
        val runeWordName = "breath_of_the_dying"
        val expected = DetailState.Content(
            value = RuneWordsVO.fromDto(useCase.execute(runeWordName).getOrThrow())
        )

        // when
        viewModel.fetchDetail(runeWordName)

        // then
        assertEquals(expected = expected, actual = viewModel.state)
    }
}
