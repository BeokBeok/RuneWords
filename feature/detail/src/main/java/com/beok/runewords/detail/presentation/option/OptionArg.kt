package com.beok.runewords.detail.presentation.option

/**
 * 옵션 형식 문자열의 placeholder(%1$s, %2$s, ...)에 순서대로 들어가는 인자.
 *
 * 수치 표기(범위 구분자, 캐릭터 레벨 비례 표기)는 [RuneWordOptionFormatter]가 언어별 문자열 리소스 하나로 만든다.
 * 그래서 룬워드마다 수치 표기가 달라지지 않는다.
 */
internal sealed interface OptionArg {

    /** 아이템마다 수치가 달라지는 옵션이면 true. 화면에서 강조 색상으로 표시한다. */
    val isVariable: Boolean get() = false

    /** 고정 수치. 예: 공격 속도 +30% */
    data class Fixed(val value: Number) : OptionArg

    /** 범위 수치. 예: 피해 +300~390% 증가 */
    data class Range(val min: Number, val max: Number) : OptionArg {
        override val isVariable: Boolean get() = true
    }

    /** 캐릭터 레벨 비례 수치. 예: 최대 피해 +(캐릭터 레벨 x 3) */
    data class PerLevel(val factor: Number) : OptionArg

    /** 캐릭터 레벨 비례 수치이면서 계수가 범위인 수치. 예: 생명력 +(캐릭터 레벨 x 1~1.5) */
    data class PerLevelRange(val minFactor: Number, val maxFactor: Number) : OptionArg {
        override val isVariable: Boolean get() = true
    }

    /** 기술 이름. 예: 15 레벨 냉기 갑옷 시전 */
    data class SkillName(val skill: Skill) : OptionArg
}
