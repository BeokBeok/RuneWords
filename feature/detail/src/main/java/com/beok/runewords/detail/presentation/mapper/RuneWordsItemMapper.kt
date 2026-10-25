package com.beok.runewords.detail.presentation.mapper

import android.content.Context
import com.beok.runewords.common.ext.resourceIDByName
import com.beok.runewords.common.model.Rune
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import com.beok.runewords.detail.presentation.model.RuneWordsItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class RuneWordsItemMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun toItem(runeWordsDetail: RuneWordsDetail): RuneWordsItem = RuneWordsItem(
        name = runeWordsDetail.name,
        nameResourceID = context.resourceIDByName(runeWordsDetail.name),
        runeCombination = runeWordsDetail.runeCombination.mapNotNull(Rune::findByName),
        typeResourceIDs = runeWordsDetail.type
            .map(context::resourceIDByName)
            .filter { it > 0 },
        optionResourceID = context.resourceIDByName(runeWordsDetail.option),
        levelLimit = runeWordsDetail.levelLimit
    )
}
