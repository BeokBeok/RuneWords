package com.beok.runewords.common.option

import com.beok.runewords.common.option.XmlStringResources.Companion.EN
import com.beok.runewords.common.option.XmlStringResources.Companion.KO
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

internal class OptionFilterLabelTest {

    private val invalid = Regex("""[0-9%$+~×]|\(\s*\)|^\s|\s$|^to """)

    @Test
    fun `모든 옵션 형식의 칩 이름은_수치 자리가 남지 않아야 한다`() {
        listOf(EN, KO).forEach { resources ->
            OptionType.entries.forEach { type ->
                val label = OptionFilterLabel.label(type, resources::template)
                assertTrue(label.isNotBlank(), "$type 의 칩 이름이 비어 있습니다.")
                assertTrue(!invalid.containsMatchIn(label), "$type 의 칩 이름에 수치 자리가 남았습니다: $label")
            }
        }
    }

    @Test
    fun `특정 기술 형식 3개는_칩 이름이 같아야 한다`() {
        val types = listOf(OptionType.SKILL_BONUS, OptionType.CLASS_ONLY_SKILL, OptionType.SKILL_CHARGES)
        assertEquals(setOf("특정 기술"), types.map { OptionFilterLabel.label(it, KO::template) }.toSet())
        assertEquals(setOf("Specific skills"), types.map { OptionFilterLabel.label(it, EN::template) }.toSet())
    }

    @Test
    fun `형식 문자열에서_수치 자리를 지운다`() {
        assertEquals("공격 속도", OptionFilterLabel.label(OptionType.INCREASED_ATTACK_SPEED, KO::template))
        assertEquals("Strength", OptionFilterLabel.label(OptionType.STRENGTH, EN::template))
        assertEquals("Damage reduced", OptionFilterLabel.label(OptionType.DAMAGE_REDUCED, EN::template))
        assertEquals(
            expected = "Adds cold damage (seconds)",
            actual = OptionFilterLabel.stripValues("Adds %1\$s-%2\$s cold damage (%3\$s seconds)")
        )
    }
}
