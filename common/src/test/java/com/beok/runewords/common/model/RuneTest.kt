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

    @Test
    fun `현재 룬의 이전 룬을 찾습니다`() {
        val rune = Rune.ZOD

        val actual = Rune.previous(rune)

        assertThat(actual).isEqualTo(Rune.CHAM)
    }

    @Test
    fun `현재 룬이 EL이면_이전 룬은 EL입니다`() {
        val rune = Rune.EL

        val actual = Rune.previous(rune)

        assertThat(actual).isEqualTo(Rune.EL)
    }
}
