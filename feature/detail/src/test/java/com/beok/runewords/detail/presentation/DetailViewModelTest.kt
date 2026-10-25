package com.beok.runewords.detail.presentation

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.beok.runewords.common.test.MainCoroutineTestExtension
import com.beok.runewords.detail.domain.RuneWordsDetailRepository
import com.beok.runewords.detail.presentation.mapper.RuneWordsItemMapper
import com.beok.runewords.detail.presentation.model.DetailState
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class DetailViewModelTest {

    private val repository: RuneWordsDetailRepository = RuneWordsDetailRepository.Fake()
    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier(any(), "string", any()) } returns 1
        }
    }
    private lateinit var viewModel: DetailViewModel

    @Test
    fun `룬워드 상세 정보를_불러옵니다`() = runTest {
        // given
        viewModel = DetailViewModel(
            savedStateHandle = SavedStateHandle(mapOf("runeWordName" to "breath_of_the_dying")),
            runeWordsDetailRepository = repository,
            runeWordsItemMapper = RuneWordsItemMapper(context)
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
