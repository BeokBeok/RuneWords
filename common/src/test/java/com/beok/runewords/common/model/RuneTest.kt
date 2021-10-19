package com.beok.runewords.common.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RuneTest {

    @Test
    fun `모든 룬의 리스트를 불러옵니다`() {
        val rune = Rune.all()

        assertThat(rune.size).isEqualTo(33)
    }

    @Test
    fun `룬의 이름에 해당하는 Enum을 찾습니다`() {
        val runeName = "vex"

        val actual = Rune.findByName(name = runeName)

        assertThat(actual).isEqualTo(Rune.VEX)
    }
}
