package com.beok.runewords.detail.presentation.option

import com.beok.runewords.detail.presentation.option.OptionArg.Fixed
import com.beok.runewords.detail.presentation.option.OptionArg.PerLevel
import com.beok.runewords.detail.presentation.option.OptionArg.PerLevelRange
import com.beok.runewords.detail.presentation.option.OptionArg.Range
import com.beok.runewords.detail.presentation.option.OptionArg.SkillName
import com.beok.runewords.detail.presentation.option.OptionType.ALL_RESISTANCES
import com.beok.runewords.detail.presentation.option.OptionType.ATTACK_RATING
import com.beok.runewords.detail.presentation.option.OptionType.CANNOT_BE_FROZEN
import com.beok.runewords.detail.presentation.option.OptionType.CHANCE_TO_CAST_WHEN_STRUCK
import com.beok.runewords.detail.presentation.option.OptionType.CRUSHING_BLOW
import com.beok.runewords.detail.presentation.option.OptionType.DAMAGE_REDUCED
import com.beok.runewords.detail.presentation.option.OptionType.DAMAGE_TAKEN_GOES_TO_MANA
import com.beok.runewords.detail.presentation.option.OptionType.DEADLY_STRIKE
import com.beok.runewords.detail.presentation.option.OptionType.DEFENSE
import com.beok.runewords.detail.presentation.option.OptionType.ENHANCED_DAMAGE
import com.beok.runewords.detail.presentation.option.OptionType.ENHANCED_DEFENSE
import com.beok.runewords.detail.presentation.option.OptionType.FASTER_CAST_RATE
import com.beok.runewords.detail.presentation.option.OptionType.HIT_BLINDS_TARGET
import com.beok.runewords.detail.presentation.option.OptionType.HIT_CAUSES_MONSTER_TO_FLEE
import com.beok.runewords.detail.presentation.option.OptionType.INCREASED_ATTACK_SPEED
import com.beok.runewords.detail.presentation.option.OptionType.INDESTRUCTIBLE
import com.beok.runewords.detail.presentation.option.OptionType.LIFE
import com.beok.runewords.detail.presentation.option.OptionType.LIFE_STOLEN_PER_HIT
import com.beok.runewords.detail.presentation.option.OptionType.LIGHT_RADIUS
import com.beok.runewords.detail.presentation.option.OptionType.MAGIC_FIND
import com.beok.runewords.detail.presentation.option.OptionType.MAXIMUM_DAMAGE
import com.beok.runewords.detail.presentation.option.OptionType.MAXIMUM_LIGHTNING_RESIST
import com.beok.runewords.detail.presentation.option.OptionType.MINIMUM_DAMAGE
import com.beok.runewords.detail.presentation.option.OptionType.REGENERATE_MANA
import com.beok.runewords.detail.presentation.option.OptionType.REPLENISH_LIFE
import com.beok.runewords.detail.presentation.option.OptionType.SKILL_BONUS
import com.beok.runewords.detail.presentation.option.OptionType.SLOWS_TARGET

/**
 * 룬워드별 옵션 정의. 키는 Firestore의 룬워드 `name`이다.
 *
 * 여기에 정의가 없는 룬워드는 기존 `rune_word_*_option` HTML 리소스를 그대로 표시한다.
 * 이전이 끝난 룬워드는 기존 HTML 리소스를 삭제한다.
 */
internal object RuneWordOptionCatalog {

    fun find(runeWordName: String): List<RuneWordOptionSection>? = definitions[runeWordName]

    val runeWordNames: Set<String> get() = definitions.keys

    private val eternity = listOf(
        RuneWordOptionSection(
            options = listOf(
                option(INDESTRUCTIBLE),
                option(INCREASED_ATTACK_SPEED, Fixed(30)),
                option(ENHANCED_DAMAGE, Range(300, 390)),
                option(MINIMUM_DAMAGE, Fixed(9)),
                option(MAXIMUM_DAMAGE, PerLevel(3)),
                option(LIFE_STOLEN_PER_HIT, Fixed(7)),
                option(CRUSHING_BLOW, Fixed(20)),
                option(HIT_BLINDS_TARGET),
                option(SLOWS_TARGET, Fixed(33)),
                option(REGENERATE_MANA, Fixed(16)),
                option(REPLENISH_LIFE, Fixed(16)),
                option(CANNOT_BE_FROZEN),
                option(MAGIC_FIND, Fixed(30)),
                option(SKILL_BONUS, Fixed(5), SkillName(Skill.REVIVE))
            )
        )
    )

    private val fortitude = listOf(
        RuneWordOptionSection(
            title = OptionSectionTitle.BODY_ARMOR,
            options = listOf(
                option(CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(15), SkillName(Skill.CHILLING_ARMOR)),
                option(FASTER_CAST_RATE, Fixed(25)),
                option(ENHANCED_DAMAGE, Fixed(300)),
                option(ENHANCED_DEFENSE, Fixed(200)),
                option(DEFENSE, Fixed(15)),
                option(LIFE, PerLevelRange(1, 1.5)),
                option(REPLENISH_LIFE, Fixed(7)),
                option(MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
                option(ALL_RESISTANCES, Range(25, 30)),
                option(DAMAGE_REDUCED, Fixed(7)),
                option(DAMAGE_TAKEN_GOES_TO_MANA, Fixed(12)),
                option(LIGHT_RADIUS, Fixed(1))
            )
        ),
        RuneWordOptionSection(
            title = OptionSectionTitle.WEAPON,
            options = listOf(
                option(CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(15), SkillName(Skill.CHILLING_ARMOR)),
                option(FASTER_CAST_RATE, Fixed(25)),
                option(ENHANCED_DAMAGE, Fixed(300)),
                option(MINIMUM_DAMAGE, Fixed(9)),
                option(ATTACK_RATING, Fixed(50)),
                option(DEADLY_STRIKE, Fixed(20)),
                option(HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
                option(ENHANCED_DEFENSE, Fixed(200)),
                option(LIFE, PerLevelRange(1, 1.5)),
                option(ALL_RESISTANCES, Range(25, 30)),
                option(DAMAGE_TAKEN_GOES_TO_MANA, Fixed(12)),
                option(LIGHT_RADIUS, Fixed(1))
            )
        )
    )

    private val definitions: Map<String, List<RuneWordOptionSection>> = mapOf(
        "eternity" to eternity,
        "fortitude" to fortitude
    )
}
