package com.beok.runewords.common.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuneTest {

    @Test
    fun `모든 룬의 리스트를 불러옵니다`() {
        val rune = Rune.all()

        assertThat(rune.size).isEqualTo(33)
    }
}
