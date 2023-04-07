package com.beok.runewords.combination.presentation

import androidx.lifecycle.SavedStateHandle
import com.beok.runewords.combination.domain.RuneWordsRepository
import com.beok.runewords.combination.presentation.vo.CombinationState
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.model.Rune
import com.beok.runewords.common.test.MainCoroutineTestExtension
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class CombinationViewModelTest {

    private val repository: RuneWordsRepository = RuneWordsRepository.Fake()
    private lateinit var viewModel: CombinationViewModel

    @Test
    fun `룬 기준으로 룬워드 리스트를_불러옵니다`() = runBlocking {
        // given
        viewModel = CombinationViewModel(
            savedStateHandle = SavedStateHandle().apply {
                set(BundleKeyConstants.RUNE_NAME, Rune.ZOD.name)
            },
            runeWordsRepository = repository
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
