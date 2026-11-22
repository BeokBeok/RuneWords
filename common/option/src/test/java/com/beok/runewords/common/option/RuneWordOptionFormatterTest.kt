package com.beok.runewords.common.option

import com.beok.runewords.common.option.OptionArg.Fixed
import com.beok.runewords.common.option.OptionArg.PerLevel
import com.beok.runewords.common.option.OptionArg.PerLevelRange
import com.beok.runewords.common.option.OptionArg.Range
import com.beok.runewords.common.option.XmlStringResources.Companion.EN
import com.beok.runewords.common.option.XmlStringResources.Companion.KO
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class RuneWordOptionFormatterTest {

    private val koFormatter = RuneWordOptionFormatter(KO::getString)
    private val enFormatter = RuneWordOptionFormatter(EN::getString)

    @Test
    fun `모든 룬워드의 옵션을_en과 ko로 빈 줄 없이 표시한다`() {
        val formatSpecifier = Regex("""%(\d+\${'$'})?s|%%""")
        RuneWordOptionCatalog.runeWordNames.forEach { name ->
            val sections = RuneWordOptionCatalog.find(name).orEmpty()
            listOf(enFormatter, koFormatter).forEach { formatter ->
                val lines = formatter.format(sections).flatMap(FormattedOptionSection::lines)
                assertEquals(
                    expected = sections.sumOf { it.options.size },
                    actual = lines.count { it.text.isNotBlank() && !formatSpecifier.containsMatchIn(it.text) },
                    message = "$name 의 옵션 문구가 비었거나 형식 문자가 남았습니다."
                )
            }
        }
    }

    @Test
    fun `피해 범위는_범위로 표시하지만 강조하지 않는다`() {
        // given
        val sections = listOf(
            RuneWordOptionSection(options = listOf(option(OptionType.ADDS_COLD_DAMAGE, OptionArg.DamageRange(3, 14))))
        )

        // when
        val actual = koFormatter.format(sections).single().lines.single()

        // then
        assertEquals(expected = FormattedOptionLine(text = "냉기 피해 3~14 추가", isVariable = false), actual = actual)
    }

    @Test
    fun `영원의 옵션을_한국어로 표시한다`() {
        // given
        val sections = RuneWordOptionCatalog.find("eternity").orEmpty()

        // when
        val actual = koFormatter.format(sections)

        // then
        assertEquals(
            expected = listOf(
                "파괴 불가",
                "공격 속도 +30%",
                "피해 +300~390% 증가",
                "최소 피해 +9",
                "최대 피해 +(캐릭터 레벨 x 3)",
                "적중당 생명력 7% 훔침",
                "강타 확률 20%",
                "적중 시 대상 실명",
                "대상 감속 33%",
                "마나 재생 16%",
                "생명력 회복 +16",
                "빙결되지 않음",
                "마법 아이템 발견 확률 30% 증가",
                "+5 부활"
            ),
            actual = actual.single().lines.map(FormattedOptionLine::text)
        )
    }

    @Test
    fun `인내의 옵션을_구역 제목과 함께 한국어로 표시한다`() {
        // given
        val sections = RuneWordOptionCatalog.find("fortitude").orEmpty()

        // when
        val actual = koFormatter.format(sections)

        // then
        assertEquals(expected = listOf("갑옷", "무기"), actual = actual.map(FormattedOptionSection::title))
        assertEquals(
            expected = "피격 시 20% 확률로 15 레벨 냉기 갑옷 시전",
            actual = actual.first().lines.first().text
        )
        assertEquals(
            expected = FormattedOptionLine(text = "생명력 +(캐릭터 레벨 x 1~1.5)", isVariable = true),
            actual = actual.first().lines.first { it.text.startsWith("생명력 +") }
        )
    }

    @Test
    fun `캐릭터 레벨 비례 옵션은_룬워드가 달라도 같은 형식으로 표시한다`() {
        // given
        val sections = listOf(
            RuneWordOptionSection(
                options = listOf(
                    option(OptionType.MAXIMUM_DAMAGE, PerLevel(3)),
                    option(OptionType.LIFE, PerLevelRange(1, 1.5))
                )
            )
        )

        // when
        val ko = koFormatter.format(sections).single().lines.map(FormattedOptionLine::text)
        val en = enFormatter.format(sections).single().lines.map(FormattedOptionLine::text)

        // then
        assertEquals(expected = listOf("최대 피해 +(캐릭터 레벨 x 3)", "생명력 +(캐릭터 레벨 x 1~1.5)"), actual = ko)
        assertEquals(
            expected = listOf("+(character level x 3) to maximum damage", "+(character level x 1-1.5) to life"),
            actual = en
        )
    }

    @Test
    fun `범위 수치가 있는 옵션만_수치가 달라지는 옵션으로 표시한다`() {
        // given
        val sections = listOf(
            RuneWordOptionSection(
                options = listOf(
                    option(OptionType.ENHANCED_DAMAGE, Range(300, 390)),
                    option(OptionType.ENHANCED_DAMAGE, Fixed(300)),
                    option(OptionType.MAXIMUM_DAMAGE, PerLevel(3)),
                    option(OptionType.LIFE, PerLevelRange(1, 1.5))
                )
            )
        )

        // when
        val actual = enFormatter.format(sections).single().lines.map(FormattedOptionLine::isVariable)

        // then
        assertEquals(expected = listOf(true, false, false, true), actual = actual)
    }

    @Test
    fun `수치는_소수점 뒤의 불필요한 0을 지워서 표시한다`() {
        // given
        val sections = listOf(
            RuneWordOptionSection(
                options = listOf(
                    option(OptionType.LIFE, Fixed(300.0)),
                    option(OptionType.LIFE, PerLevel(0.375)),
                    option(OptionType.LIFE, PerLevelRange(1.0, 1.50))
                )
            )
        )

        // when
        val actual = enFormatter.format(sections).single().lines.map(FormattedOptionLine::text)

        // then
        assertEquals(
            expected = listOf(
                "+300 to life",
                "+(character level x 0.375) to life",
                "+(character level x 1-1.5) to life"
            ),
            actual = actual
        )
    }

    @Test
    fun `한국어 기술 보너스는_게임 툴팁처럼 수치를 기술 이름 앞에 표시한다`() {
        // given
        val sections = listOf(
            RuneWordOptionSection(
                options = listOf(
                    option(
                        OptionType.CLASS_ONLY_SKILL,
                        Fixed(3),
                        OptionArg.SkillName(Skill.ABYSS),
                        OptionArg.ClassName(CharacterClass.WARLOCK)
                    ),
                    option(OptionType.SKILL_BONUS, Fixed(5), OptionArg.SkillName(Skill.REVIVE))
                )
            )
        )

        // when
        val actual = koFormatter.format(sections).single().lines.map(FormattedOptionLine::text)

        // then
        assertEquals(expected = listOf("+3 심연 (악마술사 전용)", "+5 부활"), actual = actual)
    }
}
