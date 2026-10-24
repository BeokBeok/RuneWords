package com.beok.runewords.inapp.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AppVersionTest {

    @Test
    fun `3_9_0은 3_10_0보다 낮은 버전입니다`() {
        val actual = version("3.9.0") < version("3.10.0")

        assertThat(actual).isTrue()
    }

    @Test
    fun `앞자리부터 숫자로 비교합니다`() {
        assertThat(version("4.0.0") > version("3.99.99")).isTrue()
        assertThat(version("3.4.10") > version("3.4.9")).isTrue()
        assertThat(version("3.4.0").compareTo(version("3.4.0"))).isEqualTo(0)
    }

    @Test
    fun `자릿수가 다르면 부족한 자리를 0으로 보고 비교합니다`() {
        assertThat(version("3.10")).isEqualTo(version("3.10.0"))
        assertThat(version("3.10").hashCode()).isEqualTo(version("3.10.0").hashCode())
        assertThat(version("3.10.1") > version("3.10")).isTrue()
        assertThat(version("3") < version("3.0.1")).isTrue()
    }

    @Test
    fun `형식이 잘못된 버전은 null을 반환합니다`() {
        assertThat(AppVersion.parse("")).isNull()
        assertThat(AppVersion.parse("3..0")).isNull()
        assertThat(AppVersion.parse("3.a.0")).isNull()
        assertThat(AppVersion.parse("3.-1.0")).isNull()
        assertThat(AppVersion.parse("3.4.0-beta")).isNull()
    }

    private fun version(value: String): AppVersion = requireNotNull(AppVersion.parse(value))
}
