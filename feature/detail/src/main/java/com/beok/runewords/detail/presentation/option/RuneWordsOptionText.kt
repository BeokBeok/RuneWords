package com.beok.runewords.detail.presentation.option

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import com.beok.runewords.detail.presentation.model.RuneWordsItem

/**
 * 룬워드 옵션 문구를 만든다. 옵션 정의가 없으면 null을 돌려준다.
 * 언어가 바뀌면 문구를 다시 만든다.
 */
@Suppress("SpreadOperator")
@Composable
internal fun rememberRuneWordsOptionText(info: RuneWordsItem): AnnotatedString? {
    val sections = info.optionSections
    val configuration = LocalConfiguration.current
    val resources = LocalContext.current.resources
    return remember(sections, configuration) {
        if (sections.isEmpty()) return@remember null
        RuneWordOptionFormatter { resID, args -> resources.getString(resID, *args) }
            .format(sections)
            .toAnnotatedString()
    }
}
