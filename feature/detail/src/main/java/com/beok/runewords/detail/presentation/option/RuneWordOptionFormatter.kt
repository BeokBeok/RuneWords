package com.beok.runewords.detail.presentation.option

import com.beok.runewords.detail.R
import java.math.BigDecimal

internal data class FormattedOptionLine(val text: String, val isVariable: Boolean)

internal data class FormattedOptionSection(val title: String?, val lines: List<FormattedOptionLine>)

/**
 * 옵션 형식과 인자를 표시 문자열로 바꾼다.
 *
 * [getString]은 문자열 리소스 ID와 format 인자를 받아 현재 언어의 문자열을 돌려준다.
 * 화면에서는 `Resources.getString`을 넘긴다.
 */
internal class RuneWordOptionFormatter(
    private val getString: (resID: Int, args: Array<out Any>) -> String
) {

    fun format(sections: List<RuneWordOptionSection>): List<FormattedOptionSection> = sections.map { section ->
        FormattedOptionSection(
            title = section.title?.let { getString(it.titleResID, emptyArray()) },
            lines = section.options.map(::formatOption)
        )
    }

    private fun formatOption(option: RuneWordOption) = FormattedOptionLine(
        text = getString(option.type.templateResID, option.args.map(::formatArg).toTypedArray()),
        isVariable = option.args.any(OptionArg::isVariable)
    )

    private fun formatArg(arg: OptionArg): String = when (arg) {
        is OptionArg.Fixed -> arg.value.toPlainString()
        is OptionArg.Range -> formatRange(arg.min, arg.max)
        is OptionArg.DamageRange -> formatRange(arg.min, arg.max)
        is OptionArg.PerLevel -> getString(
            R.string.option_value_per_level,
            arrayOf(arg.factor.toPlainString())
        )
        is OptionArg.PerLevelRange -> getString(
            R.string.option_value_per_level_range,
            arrayOf(arg.minFactor.toPlainString(), arg.maxFactor.toPlainString())
        )
        is OptionArg.SkillName -> getString(arg.skill.nameResID, emptyArray())
        is OptionArg.ClassName -> getString(arg.characterClass.nameResID, emptyArray())
    }

    private fun formatRange(min: Number, max: Number): String = getString(
        R.string.option_value_range,
        arrayOf(min.toPlainString(), max.toPlainString())
    )

    /** 소수점 뒤의 불필요한 0을 지운다. 예: 1.50 -> 1.5, 300.0 -> 300 */
    private fun Number.toPlainString(): String = BigDecimal(toString()).stripTrailingZeros().toPlainString()
}
