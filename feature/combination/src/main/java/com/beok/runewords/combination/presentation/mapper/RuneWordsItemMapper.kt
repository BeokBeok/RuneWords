package com.beok.runewords.combination.presentation.mapper

import android.content.Context
import com.beok.runewords.combination.domain.model.RuneWords
import com.beok.runewords.combination.presentation.model.RuneWordsItem
import com.beok.runewords.common.ext.resourceIDByName
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class RuneWordsItemMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun toItems(runeWords: List<RuneWords>): List<RuneWordsItem> = runeWords
        .distinctBy(RuneWords::name)
        .mapNotNull { item ->
            val nameResourceID = context.resourceIDByName(item.name)
            if (nameResourceID < 1) return@mapNotNull null
            RuneWordsItem(
                name = item.name,
                nameResourceID = nameResourceID,
                mode = item.mode
            )
        }
}
