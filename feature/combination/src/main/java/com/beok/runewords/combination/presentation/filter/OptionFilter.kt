package com.beok.runewords.combination.presentation.filter

import androidx.compose.runtime.Immutable
import com.beok.runewords.common.option.OptionArg
import com.beok.runewords.common.option.OptionType
import com.beok.runewords.common.option.RuneWordOption
import com.beok.runewords.common.option.Skill
import java.text.Collator

/**
 * 옵션 필터 칩 하나. [types] 중 하나라도 가진 룬워드가 이 칩에 맞는다.
 * [key]는 언어와 관계없는 식별자다. 칩에 속한 형식 이름 중 가장 앞의 이름을 쓴다.
 */
@Immutable
internal data class OptionFilterChip(
    val key: String,
    val label: String,
    val types: Set<OptionType>,
    val runeWordCount: Int
)

/**
 * 룬 하나의 룬워드 옵션으로 필터 칩을 만들고 룬워드를 거른다.
 * 여러 칩을 고르면 고른 칩에 모두 맞는 룬워드만 남는다.
 */
@Immutable
internal class OptionFilter(
    private val optionsByRuneWord: Map<String, List<RuneWordOption>>,
    labelOf: (OptionType) -> String,
    labelComparator: Comparator<String> = Collator.getInstance().let { collator -> Comparator(collator::compare) }
) {

    /**
     * 칩 목록. 칩 이름 순서(ko 가나다, en abc)다.
     * 묶음 없이 나열한다. 사용자가 많이 고르는 옵션을 Analytics로 모은 뒤 묶음이나 고정 구역을 정한다.
     * 인기와 관계없는 순서로 두어서 화면 위치 때문에 선택 수가 치우치는 것을 줄인다.
     */
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
                    runeWordCount = optionsByRuneWord.values.count { options -> options.any { it.type in typeSet } }
                )
            }
            .sortedWith(compareBy(labelComparator, OptionFilterChip::label))
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
}
