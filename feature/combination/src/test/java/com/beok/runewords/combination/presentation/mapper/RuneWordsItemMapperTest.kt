package com.beok.runewords.combination.presentation.mapper

import android.content.Context
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.combination.presentation.model.RuneWordsItem
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class RuneWordsItemMapperTest {

    private val resourceIDs = mapOf("breath_of_the_dying" to 1, "mosaic" to 2)
    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier(any(), "string", any()) } answers {
                resourceIDs[firstArg()] ?: 0
            }
            every { getStringArray(any()) } returns arrayOf("mosaic")
        }
    }

    @Test
    fun `리소스가 없는 룬워드를 거르고 스탠다드 전용 여부를 표시합니다`() {
        val actual = RuneWordsItemMapper(context).toItems(
            listOf(
                RuneWords(name = "breath_of_the_dying"),
                RuneWords(name = "unknown"),
                RuneWords(name = "mosaic"),
                RuneWords(name = "mosaic")
            )
        )

        assertEquals(
            expected = listOf(
                RuneWordsItem(
                    name = "breath_of_the_dying",
                    nameResourceID = 1,
                    isStandardOnly = false
                ),
                RuneWordsItem(name = "mosaic", nameResourceID = 2, isStandardOnly = true)
            ),
            actual = actual
        )
    }
}
