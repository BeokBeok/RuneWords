package com.beok.runewords.combination.presentation

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.presentation.mapper.RuneWordsItemMapper
import com.beok.runewords.combination.presentation.model.CombinationState
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.test.MainCoroutineTestExtension
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class CombinationViewModelTest {

    private val repository: RuneWordsRepository = RuneWordsRepository.Fake()
    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier(any(), "string", any()) } returns 1
            every { getStringArray(any()) } returns emptyArray()
        }
    }
    private lateinit var viewModel: CombinationViewModel

    @Test
    fun `룬 기준으로 룬워드 리스트를_불러옵니다`() = runTest {
        // given
        viewModel = CombinationViewModel(
            savedStateHandle = SavedStateHandle().apply {
                set(BundleKeyConstants.RUNE_NAME, Rune.ZOD.name)
            },
            runeWordsRepository = repository,
            runeWordsItemMapper = RuneWordsItemMapper(context)
        )

        // when

        // then
        assertTrue(viewModel.combinationState.first() is CombinationState.Content)
        assertEquals(
            expected = 2,
            actual = (viewModel.combinationState.first() as CombinationState.Content).value.size
        )
    }
}
