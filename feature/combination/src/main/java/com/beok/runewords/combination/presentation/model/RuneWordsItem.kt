package com.beok.runewords.combination.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class RuneWordsItem(
    val name: String,
    @StringRes val nameResourceID: Int,
    val isStandardOnly: Boolean
)
