package com.beok.runewords.combination.presentation.mapper

import android.content.Context
import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.combination.presentation.model.RuneWordsItem
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class RuneWordsItemMapperTest {

    private val resourceIDs = mapOf("breath_of_the_dying" to 1, "mosaic" to 2, "hustle" to 3)
    private val context: Context = mockk {
        every { packageName } returns "com.beok.runewords"
        every { resources } returns mockk {
            every { getIdentifier(any(), "string", any()) } answers {
                resourceIDs[firstArg()] ?: 0
            }
        }
    }

    @Test
    fun `리소스가 없는 룬워드를 거르고 게임 모드를 전달합니다`() {
        val actual = RuneWordsItemMapper(context).toItems(
            listOf(
                RuneWords(name = "breath_of_the_dying"),
                RuneWords(name = "unknown"),
                RuneWords(name = "mosaic", mode = GameMode.STANDARD),
                RuneWords(name = "mosaic", mode = GameMode.STANDARD),
                RuneWords(name = "hustle", mode = GameMode.LADDER)
            )
        )

        assertEquals(
            expected = listOf(
                RuneWordsItem(
                    name = "breath_of_the_dying",
                    nameResourceID = 1,
                    mode = GameMode.ALL
                ),
                RuneWordsItem(name = "mosaic", nameResourceID = 2, mode = GameMode.STANDARD),
                RuneWordsItem(name = "hustle", nameResourceID = 3, mode = GameMode.LADDER)
            ),
            actual = actual
        )
    }
}
