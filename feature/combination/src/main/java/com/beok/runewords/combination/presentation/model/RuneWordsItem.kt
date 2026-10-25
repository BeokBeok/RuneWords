package com.beok.runewords.combination.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.beok.runewords.combination.domain.model.GameMode

@Immutable
internal data class RuneWordsItem(
    val name: String,
    @StringRes val nameResourceID: Int,
    val mode: GameMode
)
