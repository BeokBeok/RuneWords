package com.beok.runewords.detail.presentation.option

import androidx.compose.runtime.Immutable

/** 옵션 한 줄. 옵션 형식과 인자의 조합이다. */
@Immutable
internal data class RuneWordOption(
    val type: OptionType,
    val args: List<OptionArg> = emptyList()
)

/** 옵션 묶음. 아이템 종류에 따라 옵션이 달라지는 룬워드만 [title]을 가진다. */
@Immutable
internal data class RuneWordOptionSection(
    val options: List<RuneWordOption>,
    val title: OptionSectionTitle? = null
)

internal fun option(type: OptionType, vararg args: OptionArg) = RuneWordOption(type, args.toList())
