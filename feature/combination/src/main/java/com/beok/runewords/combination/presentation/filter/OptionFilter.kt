package com.beok.runewords.combination.presentation.filter

import androidx.compose.runtime.Immutable
import com.beok.runewords.common.option.OptionArg
import com.beok.runewords.common.option.OptionFilterLabel
import com.beok.runewords.common.option.OptionType
import com.beok.runewords.common.option.RuneWordOption
import com.beok.runewords.common.option.Skill

/**
 * 옵션 필터 칩 하나. [types] 중 하나라도 가진 룬워드가 이 칩에 맞는다.
 * [key]는 언어와 관계없는 식별자다. 칩에 속한 형식 이름 중 가장 앞의 이름을 쓴다.
 */
@Immutable
internal data class OptionFilterChip(
    val key: String,
    val label: String,
    val types: Set<OptionType>,
    val runeWordCount: Int,
    val isSkill: Boolean
)

/**
 * 룬 하나의 룬워드 옵션으로 필터 칩을 만들고 룬워드를 거른다.
 * 여러 칩을 고르면 고른 칩에 모두 맞는 룬워드만 남는다.
 */
@Immutable
internal class OptionFilter(
    private val optionsByRuneWord: Map<String, List<RuneWordOption>>,
    labelOf: (OptionType) -> String
) {

    val chips: List<OptionFilterChip>

    init {
        val labels = OptionType.entries.associateWith(labelOf)
        val keyByLabel = OptionType.entries.groupBy(labels::getValue)
            .mapValues { (_, types) -> types.minOf(OptionType::name) }
        chips = optionsByRuneWord.values.asSequence()
            .flatten()
            .map(RuneWordOption::type)
            .distinct()
            .groupBy(labels::getValue)
            .map { (label, types) ->
                val typeSet = types.toSet()
                OptionFilterChip(
                    key = keyByLabel.getValue(label),
                    label = label,
                    types = typeSet,
                    runeWordCount = optionsByRuneWord.values.count { options -> options.any { it.type in typeSet } },
                    isSkill = typeSet.any(OptionFilterLabel.skillTypes::contains)
                )
            }
            .sortedWith(
                compareBy<OptionFilterChip> { chip -> chip.skillOrder() }
                    .thenByDescending(OptionFilterChip::runeWordCount)
                    .thenBy(OptionFilterChip::label)
            )
    }

    private val chipByKey: Map<String, OptionFilterChip> = chips.associateBy(OptionFilterChip::key)

    /** [keys] 중 이 룬에 있는 칩의 key만 남긴다. 언어가 바뀌어 없어진 key를 버린다. */
    fun validKeys(keys: Set<String>): Set<String> = keys.filterTo(linkedSetOf(), chipByKey::containsKey)

    /** [keys]에 맞는 룬워드 이름. [keys]가 비어 있으면 모든 룬워드다. */
    fun matchingRuneWords(keys: Set<String>): Set<String> {
        val selected = keys.mapNotNull(chipByKey::get)
        return optionsByRuneWord.filterValues { options ->
            selected.all { chip -> options.any { it.type in chip.types } }
        }.keys
    }

    /** 룬워드가 [key] 칩에 맞은 옵션의 기술 이름. 예: 타격 시 기술 시전 -> 화산, 용암 바위 */
    fun matchedSkills(runeWordName: String, key: String): List<Skill> {
        val chip = chipByKey[key] ?: return emptyList()
        return optionsByRuneWord[runeWordName].orEmpty()
            .filter { it.type in chip.types }
            .flatMap(RuneWordOption::args)
            .filterIsInstance<OptionArg.SkillName>()
            .map(OptionArg.SkillName::skill)
            .distinct()
    }

    private fun OptionFilterChip.skillOrder(): Int = types
        .map(OptionFilterLabel.skillTypes::indexOf)
        .filter { it >= 0 }
        .minOrNull() ?: Int.MAX_VALUE
}
