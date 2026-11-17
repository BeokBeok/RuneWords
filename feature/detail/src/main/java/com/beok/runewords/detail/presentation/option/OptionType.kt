package com.beok.runewords.detail.presentation.option

import androidx.annotation.StringRes
import com.beok.runewords.detail.R

/**
 * 옵션 형식. 형식 하나는 언어마다 문자열 리소스 하나만 가진다.
 *
 * 새 룬워드를 추가할 때는 이미 있는 형식을 먼저 찾아서 쓴다.
 * 없는 형식만 새로 추가한다. 문자열 리소스는 `option_type_` 접두사를 붙여 en, ko에 모두 추가한다.
 */
internal enum class OptionType(@StringRes val templateResID: Int) {
    ALL_RESISTANCES(R.string.option_type_all_resistances),
    ATTACK_RATING(R.string.option_type_attack_rating),
    CANNOT_BE_FROZEN(R.string.option_type_cannot_be_frozen),
    CHANCE_TO_CAST_WHEN_STRUCK(R.string.option_type_chance_to_cast_when_struck),
    CRUSHING_BLOW(R.string.option_type_crushing_blow),
    DAMAGE_REDUCED(R.string.option_type_damage_reduced),
    DAMAGE_TAKEN_GOES_TO_MANA(R.string.option_type_damage_taken_goes_to_mana),
    DEADLY_STRIKE(R.string.option_type_deadly_strike),
    DEFENSE(R.string.option_type_defense),
    ENHANCED_DAMAGE(R.string.option_type_enhanced_damage),
    ENHANCED_DEFENSE(R.string.option_type_enhanced_defense),
    FASTER_CAST_RATE(R.string.option_type_faster_cast_rate),
    HIT_BLINDS_TARGET(R.string.option_type_hit_blinds_target),
    HIT_CAUSES_MONSTER_TO_FLEE(R.string.option_type_hit_causes_monster_to_flee),
    INCREASED_ATTACK_SPEED(R.string.option_type_increased_attack_speed),
    INDESTRUCTIBLE(R.string.option_type_indestructible),
    LIFE(R.string.option_type_life),
    LIFE_STOLEN_PER_HIT(R.string.option_type_life_stolen_per_hit),
    LIGHT_RADIUS(R.string.option_type_light_radius),
    MAGIC_FIND(R.string.option_type_magic_find),
    MAXIMUM_DAMAGE(R.string.option_type_maximum_damage),
    MAXIMUM_LIGHTNING_RESIST(R.string.option_type_maximum_lightning_resist),
    MINIMUM_DAMAGE(R.string.option_type_minimum_damage),
    REGENERATE_MANA(R.string.option_type_regenerate_mana),
    REPLENISH_LIFE(R.string.option_type_replenish_life),
    SKILL_BONUS(R.string.option_type_skill_bonus),
    SLOWS_TARGET(R.string.option_type_slows_target)
}

/** 기술 이름. 문자열 리소스는 `option_skill_` 접두사를 붙여 en, ko에 모두 추가한다. */
internal enum class Skill(@StringRes val nameResID: Int) {
    CHILLING_ARMOR(R.string.option_skill_chilling_armor),
    REVIVE(R.string.option_skill_revive)
}

/** 아이템 종류에 따라 옵션이 달라지는 룬워드의 구역 제목. 예: 인내의 갑옷/무기 */
internal enum class OptionSectionTitle(@StringRes val titleResID: Int) {
    BODY_ARMOR(R.string.option_section_body_armor),
    WEAPON(R.string.option_section_weapon)
}
