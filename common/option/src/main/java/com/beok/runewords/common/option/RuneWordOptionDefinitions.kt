package com.beok.runewords.common.option

import com.beok.runewords.common.option.OptionArg.ClassName
import com.beok.runewords.common.option.OptionArg.DamageRange
import com.beok.runewords.common.option.OptionArg.Fixed
import com.beok.runewords.common.option.OptionArg.PerLevel
import com.beok.runewords.common.option.OptionArg.PerLevelRange
import com.beok.runewords.common.option.OptionArg.Range
import com.beok.runewords.common.option.OptionArg.SkillName

/*
 * 룬워드별 옵션 정의. 키는 Firestore의 룬워드 `name`이다.
 * 옵션 순서는 게임의 영어 툴팁 순서를 따른다.
 */

private val ancientPledge = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DEFENSE, Fixed(50)),
            option(OptionType.COLD_RESIST, Fixed(43)),
            option(OptionType.FIRE_RESIST, Fixed(48)),
            option(OptionType.LIGHTNING_RESIST, Fixed(48)),
            option(OptionType.POISON_RESIST, Fixed(48)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(10))
        )
    )
)

private val authority = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(10), Fixed(15), SkillName(Skill.MIASMA_CHAIN)),
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(2), Fixed(10), SkillName(Skill.PSYCHIC_WARD)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.WARLOCK)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.ENHANCED_DAMAGE, Range(40, 60)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(15)),
            option(OptionType.FIRE_RESIST, Fixed(30)),
            option(OptionType.MAXIMUM_DURABILITY, Fixed(15)),
            option(OptionType.REQUIREMENTS, Fixed(15))
        )
    )
)

private val beast = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(9), SkillName(Skill.FANATICISM)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.ENHANCED_DAMAGE, Range(240, 270)),
            option(OptionType.CRUSHING_BLOW, Fixed(20)),
            option(OptionType.OPEN_WOUNDS, Fixed(25)),
            option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.WEREBEAR)),
            option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.LYCANTHROPY)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.STRENGTH, Range(25, 40)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2))
        )
    )
)

private val black = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(120)),
            option(OptionType.CRUSHING_BLOW, Fixed(40)),
            option(OptionType.ATTACK_RATING, Fixed(200)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(3, 14), Fixed(3)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(15)),
            option(OptionType.KNOCKBACK),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(2)),
            option(OptionType.SKILL_CHARGES, Fixed(4), Fixed(12), SkillName(Skill.CORPSE_EXPLOSION))
        )
    )
)

private val bone = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(15), Fixed(10), SkillName(Skill.BONE_ARMOR)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(15), Fixed(10), SkillName(Skill.BONE_SPEAR)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.NECROMANCER)),
            option(OptionType.MANA, Range(100, 150)),
            option(OptionType.ALL_RESISTANCES, Fixed(30)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7))
        )
    )
)

private val bramble = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Range(15, 21), SkillName(Skill.THORNS)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(50)),
            option(OptionType.POISON_SKILL_DAMAGE, Range(25, 50)),
            option(OptionType.DEFENSE, Fixed(300)),
            option(OptionType.MAXIMUM_MANA, Fixed(5)),
            option(OptionType.REGENERATE_MANA, Fixed(15)),
            option(OptionType.MAXIMUM_COLD_RESIST, Fixed(5)),
            option(OptionType.FIRE_RESIST, Fixed(30)),
            option(OptionType.POISON_RESIST, Fixed(100)),
            option(OptionType.LIFE_AFTER_EACH_KILL, Fixed(13)),
            option(OptionType.SKILL_CHARGES, Fixed(13), Fixed(33), SkillName(Skill.SPIRIT_OF_BARBS))
        )
    )
)

private val brand = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(35), Fixed(14), SkillName(Skill.AMPLIFY_DAMAGE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(100), Fixed(18), SkillName(Skill.BONE_SPEAR)),
            option(OptionType.ENHANCED_DAMAGE, Range(260, 340)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.DAMAGE_TO_DEMONS, Range(280, 330)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.KNOCKBACK),
            option(OptionType.FIRES_EXPLOSIVE_ARROWS)
        )
    )
)

private val breathOfTheDying = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_KILL, Fixed(50), Fixed(20), SkillName(Skill.POISON_NOVA)),
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(60)),
            option(OptionType.ENHANCED_DAMAGE, Range(350, 400)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(200)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Range(12, 15)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.ALL_ATTRIBUTES, Fixed(30)),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val bulwark = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Range(4, 6)),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.REPLENISH_LIFE, Fixed(30)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.PHYSICAL_DAMAGE_REDUCED_PERCENT, Range(10, 15))
        )
    )
)

private val callToArms = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(1)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.ENHANCED_DAMAGE, Range(250, 290)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.SKILL_BONUS, Range(2, 6), SkillName(Skill.BATTLE_COMMAND)),
            option(OptionType.SKILL_BONUS, Range(1, 6), SkillName(Skill.BATTLE_ORDERS)),
            option(OptionType.SKILL_BONUS, Range(1, 4), SkillName(Skill.BATTLE_CRY)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.REPLENISH_LIFE, Fixed(12)),
            option(OptionType.MAGIC_FIND, Fixed(30))
        )
    )
)

private val chainsOfHonor = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.DAMAGE_TO_DEMONS, Fixed(200)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(100)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(8)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(70)),
            option(OptionType.STRENGTH, Fixed(20)),
            option(OptionType.REPLENISH_LIFE, Fixed(7)),
            option(OptionType.ALL_RESISTANCES, Fixed(65)),
            option(OptionType.DAMAGE_REDUCED_PERCENT, Fixed(8)),
            option(OptionType.MAGIC_FIND, Fixed(25))
        )
    )
)

private val chaos = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(9), Fixed(11), SkillName(Skill.FROZEN_ORB)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(11), Fixed(9), SkillName(Skill.CHARGED_BOLT)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(35)),
            option(OptionType.ENHANCED_DAMAGE, Range(290, 340)),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(216, 471)),
            option(OptionType.OPEN_WOUNDS, Fixed(25)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.WHIRLWIND)),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.LIFE_AFTER_EACH_DEMON_KILL, Fixed(15))
        )
    )
)

private val coven = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(1)),
            option(OptionType.FASTER_CAST_RATE, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(30, 50)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.FIRE_RESIST, Fixed(30)),
            option(OptionType.LIFE_AFTER_EACH_KILL, Range(1, 5)),
            option(OptionType.MAGIC_FIND, Range(26, 40))
        )
    )
)

private val crescent = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(10), Fixed(17), SkillName(Skill.CHAIN_LIGHTNING)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(7), Fixed(13), SkillName(Skill.STATIC_FIELD)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.ENHANCED_DAMAGE, Range(180, 220)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.ENEMY_LIGHTNING_RESISTANCE, Fixed(35)),
            option(OptionType.OPEN_WOUNDS, Fixed(25)),
            option(OptionType.MAGIC_ABSORB, Range(9, 11)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.SKILL_CHARGES, Fixed(18), Fixed(30), SkillName(Skill.SUMMON_SPIRIT_WOLF))
        )
    )
)

private val cure = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(1), SkillName(Skill.CLEANSING)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.POISON_RESIST, Range(40, 60)),
            option(OptionType.POISON_LENGTH_REDUCED, Fixed(50))
        )
    )
)

private val death = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_DIE, Fixed(100), Fixed(44), SkillName(Skill.CHAIN_LIGHTNING)),
            option(OptionType.CHANCE_TO_CAST_ON_ATTACK, Fixed(25), Fixed(18), SkillName(Skill.GLACIAL_SPIKE)),
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.ENHANCED_DAMAGE, Range(300, 385)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(1, 50)),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.CRUSHING_BLOW, Fixed(50)),
            option(OptionType.DEADLY_STRIKE, PerLevel(0.5)),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.SKILL_CHARGES, Fixed(22), Fixed(15), SkillName(Skill.BLOOD_GOLEM)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val delirium = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(1), Fixed(50), SkillName(Skill.DELIRIUM)),
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(6), Fixed(14), SkillName(Skill.MIND_BLAST)),
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(14), Fixed(13), SkillName(Skill.TERROR)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(11), Fixed(18), SkillName(Skill.CONFUSE)),
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.DEFENSE, Fixed(261)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.EXTRA_GOLD, Fixed(50)),
            option(OptionType.MAGIC_FIND, Fixed(25)),
            option(OptionType.SKILL_CHARGES, Fixed(17), Fixed(60), SkillName(Skill.ATTRACT))
        )
    )
)

private val destruction = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(23), Fixed(12), SkillName(Skill.VOLCANO)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(23), SkillName(Skill.MOLTEN_BOULDER)),
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_DIE, Fixed(100), Fixed(45), SkillName(Skill.METEOR)),
            option(OptionType.CHANCE_TO_CAST_ON_ATTACK, Fixed(15), Fixed(22), SkillName(Skill.NOVA)),
            option(OptionType.ENHANCED_DAMAGE, Fixed(350)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(100, 180)),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.CRUSHING_BLOW, Fixed(20)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.DEXTERITY, Fixed(10))
        )
    )
)

private val doom = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(18), SkillName(Skill.VOLCANO)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(12), SkillName(Skill.HOLY_FREEZE)),
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(45)),
            option(OptionType.ENHANCED_DAMAGE, Range(330, 370)),
            option(OptionType.ENEMY_COLD_RESISTANCE, Range(40, 60)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.OPEN_WOUNDS, Fixed(25)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.FREEZES_TARGET_AMOUNT, Fixed(3)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val dragon = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.SHIELD,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(18), SkillName(Skill.VENOM)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(12), Fixed(15), SkillName(Skill.HYDRA)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(14), SkillName(Skill.HOLY_FIRE)),
            option(OptionType.DEFENSE, Fixed(360)),
            option(OptionType.MISSILE_DEFENSE, Fixed(230)),
            option(OptionType.ALL_ATTRIBUTES, Range(3, 5)),
            option(OptionType.STRENGTH, PerLevel(0.375)),
            option(OptionType.MANA, Fixed(50)),
            option(OptionType.MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.BODY_ARMOR,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(18), SkillName(Skill.VENOM)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(12), Fixed(15), SkillName(Skill.HYDRA)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(14), SkillName(Skill.HOLY_FIRE)),
            option(OptionType.DEFENSE, Fixed(360)),
            option(OptionType.MISSILE_DEFENSE, Fixed(230)),
            option(OptionType.ALL_ATTRIBUTES, Range(3, 5)),
            option(OptionType.STRENGTH, PerLevel(0.375)),
            option(OptionType.MAXIMUM_MANA, Fixed(5)),
            option(OptionType.MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7))
        )
    )
)

private val dream = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.HELM,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(10), Fixed(15), SkillName(Skill.CONFUSE)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(15), SkillName(Skill.HOLY_SHOCK)),
            option(OptionType.FASTER_HIT_RECOVERY, Range(20, 30)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(30)),
            option(OptionType.DEFENSE, Range(150, 220)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.MANA, PerLevel(0.625)),
            option(OptionType.ALL_RESISTANCES, Range(5, 20)),
            option(OptionType.MAGIC_FIND, Range(12, 25))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.SHIELD,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(10), Fixed(15), SkillName(Skill.CONFUSE)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(15), SkillName(Skill.HOLY_SHOCK)),
            option(OptionType.FASTER_HIT_RECOVERY, Range(20, 30)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(30)),
            option(OptionType.DEFENSE, Range(150, 220)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.LIFE, Fixed(50)),
            option(OptionType.MANA, PerLevel(0.625)),
            option(OptionType.ALL_RESISTANCES, Range(5, 20)),
            option(OptionType.MAGIC_FIND, Range(12, 25))
        )
    )
)

private val duress = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(40)),
            option(OptionType.ENHANCED_DAMAGE, Range(10, 20)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(37, 133), Fixed(2)),
            option(OptionType.CRUSHING_BLOW, Fixed(15)),
            option(OptionType.OPEN_WOUNDS, Fixed(33)),
            option(OptionType.ENHANCED_DEFENSE, Range(150, 200)),
            option(OptionType.SLOWER_STAMINA_DRAIN, Fixed(20)),
            option(OptionType.COLD_RESIST, Fixed(45)),
            option(OptionType.LIGHTNING_RESIST, Fixed(15)),
            option(OptionType.FIRE_RESIST, Fixed(15)),
            option(OptionType.POISON_RESIST, Fixed(15))
        )
    )
)

private val edge = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(15), SkillName(Skill.THORNS)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(35)),
            option(OptionType.DAMAGE_TO_DEMONS, Range(320, 380)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(280)),
            option(OptionType.POISON_DAMAGE, Fixed(75), Fixed(5)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.ALL_ATTRIBUTES, Range(5, 10)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.VENDOR_PRICES, Fixed(15))
        )
    )
)

private val enigma = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.FASTER_RUN_WALK, Fixed(45)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.TELEPORT)),
            option(OptionType.DEFENSE, Range(750, 775)),
            option(OptionType.STRENGTH, PerLevel(0.75)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.DAMAGE_REDUCED_PERCENT, Fixed(8)),
            option(OptionType.LIFE_AFTER_EACH_KILL, Fixed(14)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(15)),
            option(OptionType.MAGIC_FIND, PerLevel(1))
        )
    )
)

private val enlightenment = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(5), Fixed(15), SkillName(Skill.BLAZE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(15), SkillName(Skill.FIRE_BALL)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.SORCERESS)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.WARMTH)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(30)),
            option(OptionType.FIRE_RESIST, Fixed(30)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7))
        )
    )
)

private val eternity = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(30)),
            option(OptionType.ENHANCED_DAMAGE, Range(300, 390)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.MAXIMUM_DAMAGE, PerLevel(3)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.CRUSHING_BLOW, Fixed(20)),
            option(OptionType.HIT_BLINDS_TARGET),
            option(OptionType.SLOWS_TARGET, Fixed(33)),
            option(OptionType.REGENERATE_MANA, Fixed(16)),
            option(OptionType.REPLENISH_LIFE, Fixed(16)),
            option(OptionType.CANNOT_BE_FROZEN),
            option(OptionType.MAGIC_FIND, Fixed(30)),
            option(OptionType.SKILL_BONUS, Fixed(5), SkillName(Skill.REVIVE))
        )
    )
)

private val exile = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(15), Fixed(5), SkillName(Skill.LIFE_TAP)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(13, 16), SkillName(Skill.DEFIANCE)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(2),
                SkillName(Skill.OFFENSIVE_AURAS),
                ClassName(CharacterClass.PALADIN)
            ),
            option(OptionType.FASTER_BLOCK_RATE, Fixed(30)),
            option(OptionType.FREEZES_TARGET),
            option(OptionType.ENHANCED_DEFENSE, Range(220, 260)),
            option(OptionType.REPLENISH_LIFE, Fixed(7)),
            option(OptionType.MAXIMUM_COLD_RESIST, Fixed(5)),
            option(OptionType.MAXIMUM_FIRE_RESIST, Fixed(5)),
            option(OptionType.MAGIC_FIND, Fixed(25)),
            option(OptionType.REPAIRS_DURABILITY, Fixed(1), Fixed(4))
        )
    )
)

private val faith = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Range(12, 15), SkillName(Skill.FANATICISM)),
            option(OptionType.ALL_SKILLS, Range(1, 2)),
            option(OptionType.ENHANCED_DAMAGE, Fixed(330)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(300)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.FIRE_DAMAGE, Fixed(120)),
            option(OptionType.ALL_RESISTANCES, Fixed(15)),
            option(OptionType.REANIMATE_AS_RETURNED, Fixed(10)),
            option(OptionType.EXTRA_GOLD, Fixed(75))
        )
    )
)

private val famine = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(30)),
            option(OptionType.ENHANCED_DAMAGE, Range(320, 370)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(180, 200)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(50, 200)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(51, 250)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(50, 200)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(12)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.STRENGTH, Fixed(10))
        )
    )
)

private val flickeringFlame = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Range(4, 8), SkillName(Skill.RESIST_FIRE)),
            option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.FIRE_SKILLS)),
            option(OptionType.ENEMY_FIRE_RESISTANCE, Range(10, 15)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(30)),
            option(OptionType.MISSILE_DEFENSE, Fixed(30)),
            option(OptionType.MANA, Range(50, 75)),
            option(OptionType.HALF_FREEZE_DURATION),
            option(OptionType.MAXIMUM_FIRE_RESIST, Fixed(5)),
            option(OptionType.POISON_LENGTH_REDUCED, Fixed(50))
        )
    )
)

private val fortitude = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.BODY_ARMOR,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(15), SkillName(Skill.CHILLING_ARMOR)),
            option(OptionType.FASTER_CAST_RATE, Fixed(25)),
            option(OptionType.ENHANCED_DAMAGE, Fixed(300)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(200)),
            option(OptionType.DEFENSE, Fixed(15)),
            option(OptionType.LIFE, PerLevelRange(1, 1.5)),
            option(OptionType.REPLENISH_LIFE, Fixed(7)),
            option(OptionType.MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
            option(OptionType.ALL_RESISTANCES, Range(25, 30)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(12)),
            option(OptionType.LIGHT_RADIUS, Fixed(1))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.WEAPON,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(15), SkillName(Skill.CHILLING_ARMOR)),
            option(OptionType.FASTER_CAST_RATE, Fixed(25)),
            option(OptionType.ENHANCED_DAMAGE, Fixed(300)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(200)),
            option(OptionType.LIFE, PerLevelRange(1, 1.5)),
            option(OptionType.ALL_RESISTANCES, Range(25, 30)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(12)),
            option(OptionType.LIGHT_RADIUS, Fixed(1))
        )
    )
)

private val fury = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(209)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.OPEN_WOUNDS, Fixed(66)),
            option(OptionType.DEADLY_STRIKE, Fixed(33)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(6)),
            option(OptionType.CLASS_ONLY_SKILL, Fixed(5), SkillName(Skill.FRENZY), ClassName(CharacterClass.BARBARIAN))
        )
    )
)

private val gloom = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(15), Fixed(3), SkillName(Skill.DIM_VISION)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(10)),
            option(OptionType.ENHANCED_DEFENSE, Range(200, 260)),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.ALL_RESISTANCES, Fixed(45)),
            option(OptionType.HALF_FREEZE_DURATION),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(5)),
            option(OptionType.LIGHT_RADIUS_DECREASED, Fixed(3))
        )
    )
)

private val grief = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(35), Fixed(15), SkillName(Skill.VENOM)),
            option(OptionType.INCREASED_ATTACK_SPEED, Range(30, 40)),
            option(OptionType.DAMAGE, Range(340, 400)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.DAMAGE_TO_DEMONS, PerLevel(1.875)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.ENEMY_POISON_RESISTANCE, Range(20, 25)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.LIFE_AFTER_EACH_KILL, Range(10, 15))
        )
    )
)

private val ground = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.LIGHTNING_RESIST, Range(40, 60)),
            option(OptionType.LIGHTNING_ABSORB_PERCENT, Range(10, 15))
        )
    )
)

private val handOfJustice = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_LEVEL_UP, Fixed(100), Fixed(36), SkillName(Skill.BLAZE)),
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_DIE, Fixed(100), Fixed(48), SkillName(Skill.METEOR)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(16), SkillName(Skill.HOLY_FIRE)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(33)),
            option(OptionType.ENHANCED_DAMAGE, Range(280, 330)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.ENEMY_FIRE_RESISTANCE, Fixed(20)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.HIT_BLINDS_TARGET),
            option(OptionType.FREEZES_TARGET_AMOUNT, Fixed(3))
        )
    )
)

private val harmony = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(10), SkillName(Skill.VIGOR)),
            option(OptionType.ENHANCED_DAMAGE, Range(200, 275)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(9)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(55, 160)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(55, 160)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(55, 160)),
            option(OptionType.SKILL_BONUS, Range(2, 6), SkillName(Skill.VALKYRIE)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.REGENERATE_MANA, Fixed(20)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.LIGHT_RADIUS, Fixed(2)),
            option(OptionType.SKILL_CHARGES, Fixed(20), Fixed(25), SkillName(Skill.REVIVE))
        )
    )
)

private val heartOfOak = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(3)),
            option(OptionType.FASTER_CAST_RATE, Fixed(40)),
            option(OptionType.DAMAGE_TO_DEMONS, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_DEMONS, Fixed(100)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(3, 14), Fixed(3)),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.REPLENISH_LIFE, Fixed(20)),
            option(OptionType.MAXIMUM_MANA, Fixed(15)),
            option(OptionType.ALL_RESISTANCES, Range(30, 40)),
            option(OptionType.SKILL_CHARGES, Fixed(4), Fixed(25), SkillName(Skill.OAK_SAGE)),
            option(OptionType.SKILL_CHARGES, Fixed(14), Fixed(60), SkillName(Skill.RAVEN))
        )
    )
)

private val hearth = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.COLD_RESIST, Range(40, 60)),
            option(OptionType.COLD_ABSORB_PERCENT, Range(10, 15)),
            option(OptionType.CANNOT_BE_FROZEN)
        )
    )
)

private val holyThunder = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(60)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(21, 110)),
            option(OptionType.POISON_DAMAGE, Fixed(75), Fixed(5)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(10)),
            option(OptionType.LIGHTNING_RESIST, Fixed(60)),
            option(OptionType.MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.HOLY_SHOCK),
                ClassName(CharacterClass.PALADIN)
            ),
            option(OptionType.SKILL_CHARGES, Fixed(7), Fixed(60), SkillName(Skill.CHAIN_LIGHTNING))
        )
    )
)

private val honor = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(160)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(9)),
            option(OptionType.DEADLY_STRIKE, Fixed(25)),
            option(OptionType.ATTACK_RATING, Fixed(250)),
            option(OptionType.ALL_SKILLS, Fixed(1)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.REPLENISH_LIFE, Fixed(10)),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2))
        )
    )
)

private val hustle = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.WEAPON,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(1), SkillName(Skill.BURST_OF_SPEED)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(1), SkillName(Skill.FANATICISM)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(30)),
            option(OptionType.ENHANCED_DAMAGE, Range(180, 200)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.DEXTERITY, Fixed(10))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.BODY_ARMOR,
        options = listOf(
            option(OptionType.FASTER_RUN_WALK, Fixed(65)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.SKILL_BONUS, Fixed(6), SkillName(Skill.EVADE)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.SLOWER_STAMINA_DRAIN, Fixed(50)),
            option(OptionType.ALL_RESISTANCES, Fixed(10))
        )
    )
)

private val ice = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_LEVEL_UP, Fixed(100), Fixed(40), SkillName(Skill.BLIZZARD)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(25), Fixed(22), SkillName(Skill.FROST_NOVA)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(18), SkillName(Skill.HOLY_FREEZE)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.ENHANCED_DAMAGE, Range(140, 210)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.COLD_SKILL_DAMAGE, Range(25, 30)),
            option(OptionType.ENEMY_COLD_RESISTANCE, Fixed(20)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.EXTRA_GOLD, PerLevel(3.125))
        )
    )
)

private val infinity = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_KILL, Fixed(50), Fixed(20), SkillName(Skill.CHAIN_LIGHTNING)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(12), SkillName(Skill.CONVICTION)),
            option(OptionType.FASTER_RUN_WALK, Fixed(35)),
            option(OptionType.ENHANCED_DAMAGE, Range(255, 325)),
            option(OptionType.ENEMY_LIGHTNING_RESISTANCE, Range(45, 55)),
            option(OptionType.CRUSHING_BLOW, Fixed(40)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.VITALITY, PerLevel(0.5)),
            option(OptionType.MAGIC_FIND, Fixed(30)),
            option(OptionType.SKILL_CHARGES, Fixed(21), Fixed(30), SkillName(Skill.CYCLONE_ARMOR))
        )
    )
)

private val insight = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Range(12, 17), SkillName(Skill.MEDITATION)),
            option(OptionType.FASTER_CAST_RATE, Fixed(35)),
            option(OptionType.ENHANCED_DAMAGE, Range(200, 260)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.ATTACK_RATING_BONUS, Range(180, 250)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.POISON_DAMAGE, Fixed(75), Fixed(5)),
            option(OptionType.SKILL_BONUS, Range(1, 6), SkillName(Skill.CRITICAL_STRIKE)),
            option(OptionType.ALL_ATTRIBUTES, Fixed(5)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.MAGIC_FIND, Fixed(23))
        )
    )
)

private val kingGrace = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(100)),
            option(OptionType.DAMAGE_TO_DEMONS, Fixed(100)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(50)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(3, 14), Fixed(3)),
            option(OptionType.ATTACK_RATING, Fixed(150)),
            option(OptionType.ATTACK_RATING_AGAINST_DEMONS, Fixed(100)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(100)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7))
        )
    )
)

private val kingSlayer = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(30)),
            option(OptionType.ENHANCED_DAMAGE, Range(230, 270)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.CRUSHING_BLOW, Fixed(33)),
            option(OptionType.OPEN_WOUNDS, Fixed(50)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.VENGEANCE)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.EXTRA_GOLD, Fixed(40))
        )
    )
)

private val lastWish = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(6), Fixed(11), SkillName(Skill.FADE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(10), Fixed(18), SkillName(Skill.LIFE_TAP)),
            option(OptionType.CHANCE_TO_CAST_ON_ATTACK, Fixed(20), Fixed(20), SkillName(Skill.CHARGED_BOLT)),
            option(OptionType.AURA_WHEN_EQUIPPED, Fixed(17), SkillName(Skill.MIGHT)),
            option(OptionType.ENHANCED_DAMAGE, Range(330, 375)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.CRUSHING_BLOW, Range(60, 70)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.HIT_BLINDS_TARGET),
            option(OptionType.MAGIC_FIND, PerLevel(0.5))
        )
    )
)

private val lawBringer = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(20), Fixed(15), SkillName(Skill.DECREPIFY)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(16, 18), SkillName(Skill.SANCTUARY)),
            option(OptionType.TARGET_DEFENSE, Fixed(50)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(150, 210)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(130, 180)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.SLAIN_MONSTERS_REST_IN_PEACE),
            option(OptionType.MISSILE_DEFENSE, Range(200, 250)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.EXTRA_GOLD, Fixed(75))
        )
    )
)

private val leaf = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(5, 30)),
            option(OptionType.SKILL_BONUS, Fixed(3), SkillName(Skill.FIRE_SKILLS)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.FIRE_BOLT),
                ClassName(CharacterClass.SORCERESS)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.INFERNO),
                ClassName(CharacterClass.SORCERESS)
            ),
            option(OptionType.CLASS_ONLY_SKILL, Fixed(3), SkillName(Skill.WARMTH), ClassName(CharacterClass.SORCERESS)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.DEFENSE, PerLevel(2)),
            option(OptionType.COLD_RESIST, Fixed(33))
        )
    )
)

private val lionHeart = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(20)),
            option(OptionType.REQUIREMENTS, Fixed(15)),
            option(OptionType.STRENGTH, Fixed(25)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.VITALITY, Fixed(20)),
            option(OptionType.DEXTERITY, Fixed(15)),
            option(OptionType.LIFE, Fixed(50)),
            option(OptionType.ALL_RESISTANCES, Fixed(30))
        )
    )
)

private val lore = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(1)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.LIGHTNING_RESIST, Fixed(30)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.LIGHT_RADIUS, Fixed(2))
        )
    )
)

private val malice = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(33)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(9)),
            option(OptionType.OPEN_WOUNDS, Fixed(100)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.MONSTER_DEFENSE_PER_HIT, Fixed(100)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.DRAIN_LIFE, Fixed(5))
        )
    )
)

private val melody = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(50)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(300)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.BOW_AND_CROSSBOW_SKILLS),
                ClassName(CharacterClass.AMAZON)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.CRITICAL_STRIKE),
                ClassName(CharacterClass.AMAZON)
            ),
            option(OptionType.CLASS_ONLY_SKILL, Fixed(3), SkillName(Skill.DODGE), ClassName(CharacterClass.AMAZON)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.SLOW_MISSILES),
                ClassName(CharacterClass.AMAZON)
            ),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.KNOCKBACK)
        )
    )
)

private val memory = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(3), ClassName(CharacterClass.SORCERESS)),
            option(OptionType.FASTER_CAST_RATE, Fixed(33)),
            option(OptionType.MAXIMUM_MANA, Fixed(20)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.ENERGY_SHIELD),
                ClassName(CharacterClass.SORCERESS)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(2),
                SkillName(Skill.STATIC_FIELD),
                ClassName(CharacterClass.SORCERESS)
            ),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(9)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(50))
        )
    )
)

private val metamorphosis = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.WEREWOLF_STRIKES_GRANT_MARK, Fixed(180)),
            option(OptionType.MARK_OF_THE_WOLF),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(30)),
            option(OptionType.MAXIMUM_LIFE, Fixed(40))
        )
    ),
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.WEREBEAR_STRIKES_GRANT_MARK, Fixed(180)),
            option(OptionType.MARK_OF_THE_BEAR),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(25)),
            option(OptionType.PHYSICAL_DAMAGE_REDUCED_PERCENT, Fixed(20))
        )
    ),
    RuneWordOptionSection(
        options = listOf(
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(5),
                SkillName(Skill.SHAPE_SHIFTING_SKILLS),
                ClassName(CharacterClass.DRUID)
            ),
            option(OptionType.CRUSHING_BLOW, Fixed(25)),
            option(OptionType.ENHANCED_DEFENSE, Range(50, 80)),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.ALL_RESISTANCES, Fixed(10)),
            option(OptionType.CANNOT_BE_FROZEN)
        )
    )
)

private val mist = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.AURA_WHEN_EQUIPPED, Range(8, 12), SkillName(Skill.CONCENTRATION)),
            option(OptionType.ALL_SKILLS, Fixed(3)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.PIERCING_ATTACK, Fixed(100)),
            option(OptionType.ENHANCED_DAMAGE, Range(325, 375)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(9)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(3, 14)),
            option(OptionType.ALL_RESISTANCES, Fixed(40)),
            option(OptionType.FREEZES_TARGET_AMOUNT, Fixed(3)),
            option(OptionType.VITALITY, Fixed(24))
        )
    )
)

private val mosaic = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FINISHING_MOVES_NOT_CONSUME_CHARGES, Fixed(50)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(2),
                SkillName(Skill.MARTIAL_ARTS),
                ClassName(CharacterClass.ASSASSIN)
            ),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.ENHANCED_DAMAGE, Range(200, 250)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.COLD_SKILL_DAMAGE, Range(8, 15)),
            option(OptionType.LIGHTNING_SKILL_DAMAGE, Range(8, 15)),
            option(OptionType.FIRE_SKILL_DAMAGE, Range(8, 15)),
            option(OptionType.PREVENT_MONSTER_HEAL)
        )
    )
)

private val myth = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(3), Fixed(1), SkillName(Skill.HOWL)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(10), Fixed(1), SkillName(Skill.TAUNT)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.BARBARIAN)),
            option(OptionType.MISSILE_DEFENSE, Fixed(30)),
            option(OptionType.REPLENISH_LIFE, Fixed(10)),
            option(OptionType.ATTACKER_TAKES_DAMAGE, Fixed(14)),
            option(OptionType.REQUIREMENTS, Fixed(15))
        )
    )
)

private val nadir = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DEFENSE, Fixed(50)),
            option(OptionType.DEFENSE, Fixed(10)),
            option(OptionType.MISSILE_DEFENSE, Fixed(30)),
            option(OptionType.SKILL_CHARGES, Fixed(13), Fixed(9), SkillName(Skill.CLOAK_OF_SHADOWS)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.STRENGTH, Fixed(5)),
            option(OptionType.LESS_GOLD, Fixed(33)),
            option(OptionType.LIGHT_RADIUS_DECREASED, Fixed(3))
        )
    )
)

private val oath = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(30), Fixed(20), SkillName(Skill.BONE_SPIRIT)),
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(50)),
            option(OptionType.ENHANCED_DAMAGE, Range(210, 340)),
            option(OptionType.DAMAGE_TO_DEMONS, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_DEMONS, Fixed(100)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.MAGIC_ABSORB, Range(10, 15)),
            option(OptionType.SKILL_CHARGES, Fixed(16), Fixed(20), SkillName(Skill.HEART_OF_WOLVERINE)),
            option(OptionType.SKILL_CHARGES, Fixed(17), Fixed(14), SkillName(Skill.IRON_GOLEM))
        )
    )
)

private val obedience = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_KILL, Fixed(30), Fixed(21), SkillName(Skill.ENCHANT)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(40)),
            option(OptionType.ENHANCED_DAMAGE, Fixed(370)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(3, 14), Fixed(3)),
            option(OptionType.ENEMY_FIRE_RESISTANCE, Fixed(25)),
            option(OptionType.CRUSHING_BLOW, Fixed(40)),
            option(OptionType.DEFENSE, Range(200, 300)),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.ALL_RESISTANCES, Range(20, 30)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val obsession = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(24), Fixed(10), SkillName(Skill.WEAKEN)),
            option(OptionType.ALL_SKILLS, Fixed(4)),
            option(OptionType.FASTER_CAST_RATE, Fixed(65)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(60)),
            option(OptionType.KNOCKBACK),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Range(15, 25)),
            option(OptionType.REGENERATE_MANA, Range(15, 30)),
            option(OptionType.ALL_RESISTANCES, Range(60, 70)),
            option(OptionType.EXTRA_GOLD, Fixed(75)),
            option(OptionType.MAGIC_FIND, Fixed(30))
        )
    )
)

private val passion = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(25)),
            option(OptionType.ENHANCED_DAMAGE, Range(160, 210)),
            option(OptionType.ATTACK_RATING_BONUS, Range(50, 80)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(1, 50)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.BERSERK)),
            option(OptionType.SKILL_BONUS, Fixed(1), SkillName(Skill.ZEAL)),
            option(OptionType.HIT_BLINDS_TARGET_AMOUNT, Fixed(10)),
            option(OptionType.HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
            option(OptionType.EXTRA_GOLD, Fixed(75)),
            option(OptionType.SKILL_CHARGES, Fixed(3), Fixed(12), SkillName(Skill.HEART_OF_WOLVERINE))
        )
    )
)

private val pattern = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_BLOCK_RATE, Fixed(30)),
            option(OptionType.ENHANCED_DAMAGE, Range(40, 80)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(10)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(17, 62)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(1, 50)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(3, 14)),
            option(OptionType.POISON_DAMAGE, Fixed(75), Fixed(5)),
            option(OptionType.STRENGTH, Fixed(6)),
            option(OptionType.DEXTERITY, Fixed(6)),
            option(OptionType.ALL_RESISTANCES, Fixed(15))
        )
    )
)

private val peace = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(4), Fixed(5), SkillName(Skill.SLOW_MISSILES)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(2), Fixed(15), SkillName(Skill.VALKYRIE)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.AMAZON)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.SKILL_BONUS, Fixed(2), SkillName(Skill.CRITICAL_STRIKE)),
            option(OptionType.COLD_RESIST, Fixed(30)),
            option(OptionType.ATTACKER_TAKES_DAMAGE, Fixed(14))
        )
    )
)

private val phoenix = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.SHIELD,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_LEVEL_UP, Fixed(100), Fixed(40), SkillName(Skill.BLAZE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(40), Fixed(22), SkillName(Skill.FIRESTORM)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(10, 15), SkillName(Skill.REDEMPTION)),
            option(OptionType.ENHANCED_DAMAGE, Range(350, 400)),
            option(OptionType.ENEMY_FIRE_RESISTANCE, Fixed(28)),
            option(OptionType.MISSILE_DEFENSE, Range(350, 400)),
            option(OptionType.LIFE, Fixed(50)),
            option(OptionType.MAXIMUM_LIGHTNING_RESIST, Fixed(5)),
            option(OptionType.MAXIMUM_FIRE_RESIST, Fixed(10)),
            option(OptionType.FIRE_ABSORB, Range(15, 21))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.WEAPON,
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_YOU_LEVEL_UP, Fixed(100), Fixed(40), SkillName(Skill.BLAZE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(40), Fixed(22), SkillName(Skill.FIRESTORM)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(10, 15), SkillName(Skill.REDEMPTION)),
            option(OptionType.ENHANCED_DAMAGE, Range(350, 400)),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(14)),
            option(OptionType.ENEMY_FIRE_RESISTANCE, Fixed(28)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.MISSILE_DEFENSE, Range(350, 400)),
            option(OptionType.FIRE_ABSORB, Range(15, 21))
        )
    )
)

private val plague = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(20), Fixed(12), SkillName(Skill.LOWER_RESIST)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(25), Fixed(15), SkillName(Skill.POISON_NOVA)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(13, 17), SkillName(Skill.CLEANSING)),
            option(OptionType.ALL_SKILLS, Range(1, 2)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.ENHANCED_DAMAGE, Range(220, 320)),
            option(OptionType.ENEMY_POISON_RESISTANCE, Fixed(23)),
            option(OptionType.DEADLY_STRIKE, PerLevel(0.3)),
            option(OptionType.OPEN_WOUNDS, Fixed(25)),
            option(OptionType.FREEZES_TARGET_AMOUNT, Fixed(3))
        )
    )
)

private val pride = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(25), Fixed(17), SkillName(Skill.FIRE_WALL)),
            option(OptionType.AURA_WHEN_EQUIPPED, Range(16, 20), SkillName(Skill.CONCENTRATION)),
            option(OptionType.ATTACK_RATING_BONUS, Range(260, 300)),
            option(OptionType.DAMAGE_TO_DEMONS, PerLevel(1)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(50, 280)),
            option(OptionType.DEADLY_STRIKE, Fixed(20)),
            option(OptionType.HIT_BLINDS_TARGET),
            option(OptionType.FREEZES_TARGET_AMOUNT, Fixed(3)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.REPLENISH_LIFE, Fixed(8)),
            option(OptionType.EXTRA_GOLD, PerLevel(1.875))
        )
    )
)

private val principle = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(100), Fixed(5), SkillName(Skill.HOLY_BOLT)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.PALADIN)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(50)),
            option(OptionType.LIFE, Range(100, 150)),
            option(OptionType.SLOWER_STAMINA_DRAIN, Fixed(15)),
            option(OptionType.MAXIMUM_POISON_RESIST, Fixed(5)),
            option(OptionType.FIRE_RESIST, Fixed(30))
        )
    )
)

private val prudence = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(25)),
            option(OptionType.ENHANCED_DEFENSE, Range(140, 170)),
            option(OptionType.ALL_RESISTANCES, Range(25, 35)),
            option(OptionType.DAMAGE_REDUCED, Fixed(3)),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(17)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.REPAIRS_DURABILITY, Fixed(1), Fixed(4))
        )
    )
)

private val radiance = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DEFENSE, Fixed(75)),
            option(OptionType.MISSILE_DEFENSE, Fixed(30)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(15)),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(3)),
            option(OptionType.MANA, Fixed(33)),
            option(OptionType.DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.LIGHT_RADIUS, Fixed(5))
        )
    )
)

private val rain = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(5), Fixed(15), SkillName(Skill.CYCLONE_ARMOR)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(15), SkillName(Skill.TWISTER)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.DRUID)),
            option(OptionType.MANA, Range(100, 150)),
            option(OptionType.LIGHTNING_RESIST, Fixed(30)),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(15))
        )
    )
)

private val rhyme = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INCREASED_CHANCE_OF_BLOCKING, Fixed(20)),
            option(OptionType.FASTER_BLOCK_RATE, Fixed(40)),
            option(OptionType.ALL_RESISTANCES, Fixed(25)),
            option(OptionType.REGENERATE_MANA, Fixed(15)),
            option(OptionType.CANNOT_BE_FROZEN),
            option(OptionType.EXTRA_GOLD, Fixed(50)),
            option(OptionType.MAGIC_FIND, Fixed(25))
        )
    )
)

private val rift = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(20), Fixed(16), SkillName(Skill.TORNADO)),
            option(OptionType.CHANCE_TO_CAST_ON_ATTACK, Fixed(16), Fixed(21), SkillName(Skill.FROZEN_ORB)),
            option(OptionType.ATTACK_RATING_BONUS, Fixed(20)),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(160, 250)),
            option(OptionType.ADDS_FIRE_DAMAGE, DamageRange(60, 180)),
            option(OptionType.ALL_ATTRIBUTES, Range(5, 10)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(38)),
            option(OptionType.EXTRA_GOLD, Fixed(75)),
            option(OptionType.SKILL_CHARGES, Fixed(15), Fixed(40), SkillName(Skill.IRON_MAIDEN)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val ritual = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.ENHANCED_DAMAGE, Range(250, 320)),
            option(OptionType.ATTACK_RATING_BONUS, Range(200, 260)),
            option(OptionType.DAMAGE_TO_DEMONS, Range(150, 250)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.SLAIN_MONSTERS_REST_IN_PEACE),
            option(OptionType.LIFE_AFTER_EACH_KILL, Range(3, 5))
        )
    )
)

private val sanctuary = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.FASTER_BLOCK_RATE, Fixed(20)),
            option(OptionType.INCREASED_CHANCE_OF_BLOCKING, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(130, 160)),
            option(OptionType.MISSILE_DEFENSE, Fixed(250)),
            option(OptionType.DEXTERITY, Fixed(20)),
            option(OptionType.ALL_RESISTANCES, Range(50, 70)),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(7)),
            option(OptionType.SKILL_CHARGES, Fixed(12), Fixed(60), SkillName(Skill.SLOW_MISSILES))
        )
    )
)

private val silence = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(200)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(75)),
            option(OptionType.REQUIREMENTS, Fixed(20)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(20)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.ALL_RESISTANCES, Fixed(75)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(11)),
            option(OptionType.HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
            option(OptionType.HIT_BLINDS_TARGET_AMOUNT, Fixed(33)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.MAGIC_FIND, Fixed(30))
        )
    )
)

private val smoke = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DEFENSE, Fixed(75)),
            option(OptionType.MISSILE_DEFENSE, Fixed(280)),
            option(OptionType.ALL_RESISTANCES, Fixed(50)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.SKILL_CHARGES, Fixed(6), Fixed(18), SkillName(Skill.WEAKEN)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.LIGHT_RADIUS_DECREASED, Fixed(1))
        )
    )
)

private val spirit = listOf(
    RuneWordOptionSection(
        title = OptionSectionTitle.SHIELD,
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.FASTER_CAST_RATE, Range(25, 35)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(55)),
            option(OptionType.MISSILE_DEFENSE, Fixed(250)),
            option(OptionType.VITALITY, Fixed(22)),
            option(OptionType.MANA, Range(89, 112)),
            option(OptionType.COLD_RESIST, Fixed(35)),
            option(OptionType.LIGHTNING_RESIST, Fixed(35)),
            option(OptionType.POISON_RESIST, Fixed(35)),
            option(OptionType.MAGIC_ABSORB, Range(3, 8)),
            option(OptionType.ATTACKER_TAKES_DAMAGE, Fixed(14))
        )
    ),
    RuneWordOptionSection(
        title = OptionSectionTitle.SWORD,
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.FASTER_CAST_RATE, Range(25, 35)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(55)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(1, 50)),
            option(OptionType.ADDS_COLD_DAMAGE_DURATION, DamageRange(3, 14), Fixed(3)),
            option(OptionType.POISON_DAMAGE, Fixed(75), Fixed(5)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.MISSILE_DEFENSE, Fixed(250)),
            option(OptionType.VITALITY, Fixed(22)),
            option(OptionType.MANA, Range(89, 112)),
            option(OptionType.MAGIC_ABSORB, Range(3, 8))
        )
    )
)

private val splendor = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ALL_SKILLS, Fixed(1)),
            option(OptionType.FASTER_CAST_RATE, Fixed(10)),
            option(OptionType.FASTER_BLOCK_RATE, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(60, 100)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.REGENERATE_MANA, Fixed(15)),
            option(OptionType.EXTRA_GOLD, Fixed(50)),
            option(OptionType.MAGIC_FIND, Fixed(20)),
            option(OptionType.LIGHT_RADIUS, Fixed(3))
        )
    )
)

private val stealth = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(3)),
            option(OptionType.DEXTERITY, Fixed(6)),
            option(OptionType.MAXIMUM_STAMINA, Fixed(15)),
            option(OptionType.POISON_RESIST, Fixed(30)),
            option(OptionType.REGENERATE_MANA, Fixed(15)),
            option(OptionType.FASTER_RUN_WALK, Fixed(25)),
            option(OptionType.FASTER_CAST_RATE, Fixed(25)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(25))
        )
    )
)

private val steel = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(20)),
            option(OptionType.MINIMUM_DAMAGE, Fixed(3)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(3)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.OPEN_WOUNDS, Fixed(50)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(25)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.LIGHT_RADIUS, Fixed(1))
        )
    )
)

private val stone = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(60)),
            option(OptionType.ENHANCED_DEFENSE, Range(250, 290)),
            option(OptionType.MISSILE_DEFENSE, Fixed(300)),
            option(OptionType.STRENGTH, Fixed(16)),
            option(OptionType.VITALITY, Fixed(16)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.ALL_RESISTANCES, Fixed(15)),
            option(OptionType.SKILL_CHARGES, Fixed(16), Fixed(80), SkillName(Skill.MOLTEN_BOULDER)),
            option(OptionType.SKILL_CHARGES, Fixed(16), Fixed(16), SkillName(Skill.CLAY_GOLEM))
        )
    )
)

private val strength = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(35)),
            option(OptionType.CRUSHING_BLOW, Fixed(25)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.STRENGTH, Fixed(20)),
            option(OptionType.VITALITY, Fixed(10))
        )
    )
)

private val temper = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.MAXIMUM_LIFE, Fixed(5)),
            option(OptionType.FIRE_RESIST, Range(40, 60)),
            option(OptionType.FIRE_ABSORB_PERCENT, Range(10, 15))
        )
    )
)

private val treachery = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(5), Fixed(15), SkillName(Skill.FADE)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(25), Fixed(15), SkillName(Skill.VENOM)),
            option(OptionType.CLASS_SKILL_LEVELS, Fixed(2), ClassName(CharacterClass.ASSASSIN)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(45)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(20)),
            option(OptionType.COLD_RESIST, Fixed(30)),
            option(OptionType.EXTRA_GOLD, Fixed(50))
        )
    )
)

private val unbendingWill = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(18), Fixed(18), SkillName(Skill.TAUNT)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.COMBAT_SKILLS),
                ClassName(CharacterClass.BARBARIAN)
            ),
            option(OptionType.INCREASED_ATTACK_SPEED, Range(20, 30)),
            option(OptionType.ENHANCED_DAMAGE, Range(300, 350)),
            option(OptionType.MAXIMUM_DAMAGE, Fixed(9)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.DAMAGE_TO_UNDEAD, Fixed(75)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.LIFE_STOLEN_PER_HIT, Range(8, 10)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.STRENGTH, Fixed(10)),
            option(OptionType.VITALITY, Fixed(10)),
            option(OptionType.DAMAGE_REDUCED, Fixed(8)),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.REQUIREMENTS, Fixed(20))
        )
    )
)

private val venom = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.IGNORE_TARGET_DEFENSE),
            option(OptionType.MANA_STOLEN_PER_HIT, Fixed(7)),
            option(OptionType.SKILL_CHARGES, Fixed(15), Fixed(27), SkillName(Skill.POISON_EXPLOSION)),
            option(OptionType.SKILL_CHARGES, Fixed(13), Fixed(11), SkillName(Skill.POISON_NOVA)),
            option(OptionType.POISON_DAMAGE, Fixed(273), Fixed(6))
        )
    )
)

private val vigilance = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(5), Fixed(10), SkillName(Skill.RING_OF_FIRE)),
            option(OptionType.FASTER_RUN_WALK, Fixed(10)),
            option(OptionType.FASTER_BLOCK_RATE, Fixed(30)),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(3, 5)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(1),
                SkillName(Skill.RING_OF_FIRE),
                ClassName(CharacterClass.WARLOCK)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(1),
                SkillName(Skill.DEATH_MARK),
                ClassName(CharacterClass.WARLOCK)
            ),
            option(OptionType.ENHANCED_DEFENSE, Range(75, 100)),
            option(OptionType.LIFE, Range(20, 40)),
            option(OptionType.REPLENISH_LIFE, Fixed(7)),
            option(OptionType.MANA, Range(20, 40)),
            option(OptionType.MAXIMUM_POISON_RESIST, Fixed(5)),
            option(OptionType.ALL_RESISTANCES, Range(25, 35))
        )
    )
)

private val voiceOfReason = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(15), Fixed(13), SkillName(Skill.FROZEN_ORB)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(18), Fixed(20), SkillName(Skill.ICE_BLAST)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.DAMAGE_TO_DEMONS, Range(220, 350)),
            option(OptionType.DAMAGE_TO_UNDEAD, Range(355, 375)),
            option(OptionType.ATTACK_RATING_AGAINST_UNDEAD, Fixed(50)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(100, 220)),
            option(OptionType.ENEMY_COLD_RESISTANCE, Fixed(24)),
            option(OptionType.DEXTERITY, Fixed(10)),
            option(OptionType.CANNOT_BE_FROZEN),
            option(OptionType.EXTRA_GOLD, Fixed(75)),
            option(OptionType.LIGHT_RADIUS, Fixed(1))
        )
    )
)

private val void = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.INDESTRUCTIBLE),
            option(OptionType.ALL_SKILLS, Fixed(2)),
            option(OptionType.FASTER_CAST_RATE, Fixed(40)),
            option(OptionType.ADDS_COLD_DAMAGE, DamageRange(3, 14)),
            option(OptionType.MAGIC_SKILL_DAMAGE, Range(10, 15)),
            option(OptionType.SKILL_BONUS, Range(1, 3), SkillName(Skill.ABYSS)),
            option(OptionType.ALL_ATTRIBUTES, Range(8, 12)),
            option(OptionType.MAGIC_FIND, Fixed(30)),
            option(OptionType.SKILL_CHARGES, Fixed(4), Fixed(35), SkillName(Skill.DECREPIFY))
        )
    )
)

private val wealth = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.EXTRA_GOLD, Fixed(300)),
            option(OptionType.MAGIC_FIND, Fixed(100)),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(2)),
            option(OptionType.DEXTERITY, Fixed(10))
        )
    )
)

private val white = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.HIT_CAUSES_MONSTER_TO_FLEE, Fixed(25)),
            option(OptionType.VITALITY, Fixed(10)),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.POISON_AND_BONE_SKILLS),
                ClassName(CharacterClass.NECROMANCER)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(2),
                SkillName(Skill.BONE_SPEAR),
                ClassName(CharacterClass.NECROMANCER)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(3),
                SkillName(Skill.BONE_ARMOR),
                ClassName(CharacterClass.NECROMANCER)
            ),
            option(
                OptionType.CLASS_ONLY_SKILL,
                Fixed(4),
                SkillName(Skill.SKELETON_MASTER),
                ClassName(CharacterClass.NECROMANCER)
            ),
            option(OptionType.MAGIC_DAMAGE_REDUCED, Fixed(4)),
            option(OptionType.FASTER_CAST_RATE, Fixed(20)),
            option(OptionType.MANA, Fixed(13))
        )
    )
)

private val wind = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(10), Fixed(9), SkillName(Skill.TORNADO)),
            option(OptionType.FASTER_RUN_WALK, Fixed(20)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(40)),
            option(OptionType.FASTER_HIT_RECOVERY, Fixed(15)),
            option(OptionType.ENHANCED_DAMAGE, Range(120, 160)),
            option(OptionType.TARGET_DEFENSE, Fixed(50)),
            option(OptionType.ATTACK_RATING, Fixed(50)),
            option(OptionType.HIT_BLINDS_TARGET),
            option(OptionType.LIGHT_RADIUS, Fixed(1)),
            option(OptionType.SKILL_CHARGES, Fixed(13), Fixed(127), SkillName(Skill.TWISTER))
        )
    )
)

private val wisdom = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.PIERCING_ATTACK, Fixed(33)),
            option(OptionType.ATTACK_RATING_BONUS, Range(15, 25)),
            option(OptionType.MANA_STOLEN_PER_HIT, Range(4, 8)),
            option(OptionType.ENHANCED_DEFENSE, Fixed(30)),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.SLOWER_STAMINA_DRAIN, Fixed(15)),
            option(OptionType.CANNOT_BE_FROZEN),
            option(OptionType.MANA_AFTER_EACH_KILL, Fixed(5)),
            option(OptionType.DAMAGE_TAKEN_GOES_TO_MANA, Fixed(15))
        )
    )
)

private val wrath = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(30), Fixed(1), SkillName(Skill.DECREPIFY)),
            option(OptionType.CHANCE_TO_CAST_ON_STRIKING, Fixed(5), Fixed(10), SkillName(Skill.LIFE_TAP)),
            option(OptionType.DAMAGE_TO_DEMONS, Fixed(375)),
            option(OptionType.ATTACK_RATING_AGAINST_DEMONS, Fixed(100)),
            option(OptionType.DAMAGE_TO_UNDEAD, Range(250, 300)),
            option(OptionType.ADDS_MAGIC_DAMAGE, DamageRange(85, 120)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(41, 240)),
            option(OptionType.CRUSHING_BLOW, Fixed(20)),
            option(OptionType.PREVENT_MONSTER_HEAL),
            option(OptionType.ENERGY, Fixed(10)),
            option(OptionType.CANNOT_BE_FROZEN)
        )
    )
)

private val zephyr = listOf(
    RuneWordOptionSection(
        options = listOf(
            option(OptionType.ENHANCED_DAMAGE, Fixed(33)),
            option(OptionType.ATTACK_RATING, Fixed(66)),
            option(OptionType.ADDS_LIGHTNING_DAMAGE, DamageRange(1, 50)),
            option(OptionType.TARGET_DEFENSE, Fixed(25)),
            option(OptionType.DEFENSE, Fixed(25)),
            option(OptionType.FASTER_RUN_WALK, Fixed(25)),
            option(OptionType.INCREASED_ATTACK_SPEED, Fixed(25)),
            option(OptionType.CHANCE_TO_CAST_WHEN_STRUCK, Fixed(7), Fixed(1), SkillName(Skill.TWISTER))
        )
    )
)

// 아래 맵은 위의 정의보다 뒤에 있어야 한다. 최상위 프로퍼티는 선언 순서대로 초기화된다.
val runeWordOptionDefinitions: Map<String, List<RuneWordOptionSection>> = mapOf(
    "ancient_pledge" to ancientPledge,
    "authority" to authority,
    "beast" to beast,
    "black" to black,
    "bone" to bone,
    "bramble" to bramble,
    "brand" to brand,
    "breath_of_the_dying" to breathOfTheDying,
    "bulwark" to bulwark,
    "call_to_arms" to callToArms,
    "chains_of_honor" to chainsOfHonor,
    "chaos" to chaos,
    "coven" to coven,
    "crescent" to crescent,
    "cure" to cure,
    "death" to death,
    "delirium" to delirium,
    "destruction" to destruction,
    "doom" to doom,
    "dragon" to dragon,
    "dream" to dream,
    "duress" to duress,
    "edge" to edge,
    "enigma" to enigma,
    "enlightenment" to enlightenment,
    "eternity" to eternity,
    "exile" to exile,
    "faith" to faith,
    "famine" to famine,
    "flickering_flame" to flickeringFlame,
    "fortitude" to fortitude,
    "fury" to fury,
    "gloom" to gloom,
    "grief" to grief,
    "ground" to ground,
    "hand_of_justice" to handOfJustice,
    "harmony" to harmony,
    "heart_of_oak" to heartOfOak,
    "hearth" to hearth,
    "holy_thunder" to holyThunder,
    "honor" to honor,
    "hustle" to hustle,
    "ice" to ice,
    "infinity" to infinity,
    "insight" to insight,
    "king_grace" to kingGrace,
    "king_slayer" to kingSlayer,
    "last_wish" to lastWish,
    "law_bringer" to lawBringer,
    "leaf" to leaf,
    "lion_heart" to lionHeart,
    "lore" to lore,
    "malice" to malice,
    "melody" to melody,
    "memory" to memory,
    "metamorphosis" to metamorphosis,
    "mist" to mist,
    "mosaic" to mosaic,
    "myth" to myth,
    "nadir" to nadir,
    "oath" to oath,
    "obedience" to obedience,
    "obsession" to obsession,
    "passion" to passion,
    "pattern" to pattern,
    "peace" to peace,
    "phoenix" to phoenix,
    "plague" to plague,
    "pride" to pride,
    "principle" to principle,
    "prudence" to prudence,
    "radiance" to radiance,
    "rain" to rain,
    "rhyme" to rhyme,
    "rift" to rift,
    "ritual" to ritual,
    "sanctuary" to sanctuary,
    "silence" to silence,
    "smoke" to smoke,
    "spirit" to spirit,
    "splendor" to splendor,
    "stealth" to stealth,
    "steel" to steel,
    "stone" to stone,
    "strength" to strength,
    "temper" to temper,
    "treachery" to treachery,
    "unbending_will" to unbendingWill,
    "venom" to venom,
    "vigilance" to vigilance,
    "voice_of_reason" to voiceOfReason,
    "void_" to void,
    "wealth" to wealth,
    "white" to white,
    "wind" to wind,
    "wisdom" to wisdom,
    "wrath" to wrath,
    "zephyr" to zephyr
)
