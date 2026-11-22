package com.beok.runewords.common.option

/**
 * 옵션 필터 칩 이름을 만든다.
 *
 * 칩 이름은 옵션 형식 문자열에서 수치 자리를 지워 만든다. 그래서 형식 문자열을 고치면 칩 이름도 따라 바뀐다.
 * 기술 이름이 문장의 본체인 형식만 [labelOverrides]의 별도 문자열을 쓴다.
 * 이름이 같은 형식은 칩 하나로 합친다. 예: [OptionType.SKILL_BONUS], [OptionType.CLASS_ONLY_SKILL], [OptionType.SKILL_CHARGES]
 */
object OptionFilterLabel {

    private val labelOverrides: Map<OptionType, Int> = mapOf(
        OptionType.SKILL_BONUS to R.string.option_filter_specific_skill,
        OptionType.CLASS_ONLY_SKILL to R.string.option_filter_specific_skill,
        OptionType.SKILL_CHARGES to R.string.option_filter_specific_skill,
        OptionType.CHANCE_TO_CAST_ON_STRIKING to R.string.option_filter_cast_on_striking,
        OptionType.CHANCE_TO_CAST_WHEN_STRUCK to R.string.option_filter_cast_when_struck,
        OptionType.CHANCE_TO_CAST_ON_ATTACK to R.string.option_filter_cast_on_attack,
        OptionType.CHANCE_TO_CAST_WHEN_YOU_KILL to R.string.option_filter_cast_when_you_kill,
        OptionType.CHANCE_TO_CAST_WHEN_YOU_DIE to R.string.option_filter_cast_when_you_die,
        OptionType.CHANCE_TO_CAST_WHEN_YOU_LEVEL_UP to R.string.option_filter_cast_when_you_level_up,
        OptionType.AURA_WHEN_EQUIPPED to R.string.option_filter_aura
    )

    private val VALUE = Regex("""%\d+\$[sd]|%%|[+~×-]""")
    private val EMPTY_PARENTHESES = Regex("""\(\s*\)""")
    private val SPACE_AFTER_OPEN = Regex("""\(\s+""")
    private val SPACE_BEFORE_CLOSE = Regex("""\s+\)""")
    private val SPACES = Regex("""\s+""")
    private val EN_LEADING = Regex("""^to """)
    private val EN_TRAILING = Regex(""" (by|of)$""")

    /** [getString]은 문자열 리소스를 인자 없이 읽는다. 형식 문자열은 위치 지정자를 그대로 돌려줘야 한다. */
    fun label(type: OptionType, getString: (Int) -> String): String {
        labelOverrides[type]?.let { return getString(it) }
        return stripValues(getString(type.templateResID))
    }

    internal fun stripValues(template: String): String = template
        .replace(VALUE, "")
        .replace(EMPTY_PARENTHESES, "")
        .replace(SPACE_AFTER_OPEN, "(")
        .replace(SPACE_BEFORE_CLOSE, ")")
        .replace(SPACES, " ")
        .trim()
        .trimEnd(':')
        .replace(EN_LEADING, "")
        .replace(EN_TRAILING, "")
        .replaceFirstChar(Char::uppercaseChar)
}
