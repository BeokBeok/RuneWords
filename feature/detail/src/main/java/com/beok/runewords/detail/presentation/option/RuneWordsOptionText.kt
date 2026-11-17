package com.beok.runewords.detail.presentation.option

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import com.beok.runewords.detail.presentation.model.RuneWordsItem

/**
 * 룬워드 옵션 문구를 만든다.
 *
 * 옵션 형식으로 정의된 룬워드이면 정의로 문구를 만든다.
 * 정의가 없는 룬워드이면 기존 HTML 리소스로 문구를 만든다. 둘 다 없으면 null을 돌려준다.
 */
@Composable
internal fun rememberRuneWordsOptionText(info: RuneWordsItem): AnnotatedString? =
    if (info.optionSections.isNotEmpty()) {
        rememberStructuredOptionText(info.optionSections)
    } else {
        rememberLegacyHtmlOptionText(info.optionResourceID)
    }

/** 언어가 바뀌면 문구를 다시 만든다. */
@Suppress("SpreadOperator")
@Composable
private fun rememberStructuredOptionText(sections: List<RuneWordOptionSection>): AnnotatedString {
    val configuration = LocalConfiguration.current
    val resources = LocalContext.current.resources
    return remember(sections, configuration) {
        RuneWordOptionFormatter { resID, args -> resources.getString(resID, *args) }
            .format(sections)
            .toAnnotatedString()
    }
}

@Composable
private fun rememberLegacyHtmlOptionText(optionResourceID: Int): AnnotatedString? {
    if (optionResourceID < 1) return null
    val html = stringResource(id = optionResourceID)
    return remember(html) { AnnotatedString.fromHtml(html).withoutDefaultTextColor() }
}

/**
 * 옵션 HTML은 기본 문구에 검정색(#000000)을 지정한다.
 * 검정색 지정을 지워서 테마의 글자 색상을 따르게 한다. 강조 색상은 유지한다.
 */
private fun AnnotatedString.withoutDefaultTextColor(): AnnotatedString = AnnotatedString(
    text = text,
    spanStyles = spanStyles.map { range ->
        if (range.item.color == Color.Black) {
            range.copy(item = range.item.copy(color = Color.Unspecified))
        } else {
            range
        }
    },
    paragraphStyles = paragraphStyles
)
