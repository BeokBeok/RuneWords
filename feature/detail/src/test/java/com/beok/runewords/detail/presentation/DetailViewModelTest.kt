package com.beok.runewords.detail.presentation

import androidx.lifecycle.SavedStateHandle
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.test.MainCoroutineTestExtension
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.presentation.vo.DetailState
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class DetailViewModelTest {

    private val repository: RuneWordsDetailRepository = RuneWordsDetailRepository.Fake()
    private lateinit var viewModel: DetailViewModel

    @Test
    fun `룬워드 상세 정보를_불러옵니다`() = runBlocking {
        // given
        viewModel = DetailViewModel(
            savedStateHandle = SavedStateHandle().apply {
                set(BundleKeyConstants.RUNE_WORDS_NAME, "breath_of_the_dying")
            },
            runeWordsDetailRepository = repository
        )

        // when

        // then
        assertTrue(viewModel.detailState.first() is DetailState.Content)
        assertEquals(
            expected = "call_to_arms",
            actual = (viewModel.detailState.first() as DetailState.Content).value.name
        )
    }
}
