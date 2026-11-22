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
        OptionType.DAMAGE_REDUCED_PERCENT to "피해 감소",
        OptionType.STRENGTH to "힘",
        OptionType.DEXTERITY to "민첩",
        OptionType.FIRE_RESIST to "화염 저항",
        OptionType.MAGIC_FIND to "마법 아이템 발견"
    )

    private val filter = OptionFilter(
        optionsByRuneWord = mapOf(
            "a" to listOf(
                option(OptionType.ENHANCED_DAMAGE, Fixed(100)),
                option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(10), SkillName(Skill.VOLCANO)),
                option(OptionType.DAMAGE_REDUCED, Fixed(7)),
                option(OptionType.STRENGTH, Fixed(10)),
                option(OptionType.FIRE_RESIST, Fixed(30))
            ),
            "b" to listOf(
                option(OptionType.ENHANCED_DAMAGE, Fixed(200)),
                option(OptionType.SKILL_CHARGES, Fixed(1), Fixed(5), SkillName(Skill.VENOM)),
                option(OptionType.DEXTERITY, Fixed(10)),
                option(OptionType.MAGIC_FIND, Fixed(25))
            ),
            "c" to listOf(
                option(OptionType.ALL_SKILLS, Fixed(2)),
                option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.ZEAL)),
                option(OptionType.DAMAGE_REDUCED_PERCENT, Fixed(8))
            )
        ),
        labelOf = { labels[it] ?: it.name },
        labelComparator = naturalOrder()
    )

    @Test
    fun `칩은_이름 순서로 나열한다`() {
        assertEquals(
            expected = listOf(
                "ENHANCED_DAMAGE", "마법 아이템 발견", "모든 기술", "민첩", "타격 시 기술 시전",
                "특정 기술", "피해 감소", "화염 저항", "힘"
            ),
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
        assertEquals(setOf("ENHANCED_DAMAGE"), filter.validKeys(setOf("ENHANCED_DAMAGE", "VITALITY")))
    }

    private fun specificSkillKey(): String = filter.chips.single { it.label == "특정 기술" }.key
}
