package com.beok.runewords.detail.presentation.mapper

import android.content.Context
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import com.beok.runewords.common.option.RuneWordOptionCatalog
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

internal class RuneWordsItemMapperTest {

    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier(any(), "string", any()) } returns 1
        }
    }
    private val mapper = RuneWordsItemMapper(context)

    @Test
    fun `옵션 형식으로 정의된 룬워드는_옵션 정의를 담는다`() {
        // when
        val actual = mapper.toItem(runeWordsDetail(name = "fortitude"))

        // then
        assertEquals(expected = RuneWordOptionCatalog.find("fortitude"), actual = actual.optionSections)
    }

    @Test
    fun `옵션 형식으로 정의되지 않은 룬워드는_옵션이 비어 있다`() {
        // when
        val actual = mapper.toItem(runeWordsDetail(name = "new_rune_word"))

        // then
        assertTrue(actual.optionSections.isEmpty())
    }

    @Test
    fun `예약어 때문에 밑줄이 붙은 공허도_옵션 정의를 담는다`() {
        // when
        val actual = mapper.toItem(runeWordsDetail(name = "void_"))

        // then
        assertTrue(actual.optionSections.isNotEmpty())
    }

    private fun runeWordsDetail(name: String) = RuneWordsDetail(
        name = name,
        runeCombination = emptyList(),
        type = emptyList(),
        option = "rune_word_${name}_option",
        levelLimit = 1
    )
}
