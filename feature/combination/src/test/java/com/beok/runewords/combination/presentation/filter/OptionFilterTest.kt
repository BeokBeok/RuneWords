package com.beok.runewords.combination.presentation.filter

import com.beok.runewords.common.option.OptionArg.Fixed
import com.beok.runewords.common.option.OptionArg.SkillName
import com.beok.runewords.common.option.OptionType
import com.beok.runewords.common.option.Skill
import com.beok.runewords.common.option.option
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class OptionFilterTest {

    private val labels = mapOf(
        OptionType.SKILL_BONUS to "특정 기술",
        OptionType.SKILL_CHARGES to "특정 기술",
        OptionType.ALL_SKILLS to "모든 기술",
        OptionType.CHANCE_TO_CAST_ON_STRIKING to "타격 시 기술 시전",
        OptionType.DAMAGE_REDUCED to "피해 감소",
        OptionType.DAMAGE_REDUCED_PERCENT to "피해 감소"
    )

    private val filter = OptionFilter(
        optionsByRuneWord = mapOf(
            "a" to listOf(
                option(OptionType.ENHANCED_DAMAGE, Fixed(100)),
                option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(10), SkillName(Skill.VOLCANO)),
                option(OptionType.DAMAGE_REDUCED, Fixed(7))
            ),
            "b" to listOf(
                option(OptionType.ENHANCED_DAMAGE, Fixed(200)),
                option(OptionType.SKILL_CHARGES, Fixed(1), Fixed(5), SkillName(Skill.VENOM))
            ),
            "c" to listOf(
                option(OptionType.ALL_SKILLS, Fixed(2)),
                option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.ZEAL)),
                option(OptionType.DAMAGE_REDUCED_PERCENT, Fixed(8))
            )
        ),
        labelOf = { labels[it] ?: it.name }
    )

    @Test
    fun `기술 칩은_고정 순서로 앞에 두고 나머지는 룬워드 수 내림차순이다`() {
        assertEquals(
            expected = listOf("모든 기술", "특정 기술", "타격 시 기술 시전", "ENHANCED_DAMAGE", "피해 감소"),
            actual = filter.chips.map(OptionFilterChip::label)
        )
    }

    @Test
    fun `이름이 같은 형식은_칩 하나로 합친다`() {
        val chip = filter.chips.single { it.label == "피해 감소" }
        assertEquals(setOf(OptionType.DAMAGE_REDUCED, OptionType.DAMAGE_REDUCED_PERCENT), chip.types)
        assertEquals(2, chip.runeWordCount)
        assertEquals("DAMAGE_REDUCED", chip.key)
    }

    @Test
    fun `여러 칩을 고르면_모두 맞는 룬워드만 남긴다`() {
        assertEquals(setOf("a", "b", "c"), filter.matchingRuneWords(emptySet()))
        assertEquals(setOf("b", "c"), filter.matchingRuneWords(setOf(specificSkillKey())))
        assertEquals(setOf("a"), filter.matchingRuneWords(setOf("ENHANCED_DAMAGE", "DAMAGE_REDUCED")))
    }

    @Test
    fun `기술 칩에 맞은 옵션의_기술 이름을 돌려준다`() {
        assertEquals(listOf(Skill.VENOM), filter.matchedSkills("b", specificSkillKey()))
        assertEquals(listOf(Skill.VOLCANO), filter.matchedSkills("a", "CHANCE_TO_CAST_ON_STRIKING"))
    }

    @Test
    fun `이 룬에 없는 칩 key는_버린다`() {
        assertEquals(setOf("ENHANCED_DAMAGE"), filter.validKeys(setOf("ENHANCED_DAMAGE", "MAGIC_FIND")))
    }

    private fun specificSkillKey(): String = filter.chips.single { it.label == "특정 기술" }.key
}
