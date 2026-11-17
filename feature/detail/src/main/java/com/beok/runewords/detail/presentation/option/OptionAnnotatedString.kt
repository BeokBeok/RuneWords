package com.beok.runewords.detail.presentation.option

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

/** 수치가 아이템마다 달라지는 옵션의 강조 색상. 기존 HTML 리소스의 #cc0029와 같다. */
internal val VariableOptionColor = Color(0xFFCC0029)

/**
 * 구역 제목은 굵게 표시한다. 구역 사이에는 빈 줄을 둔다.
 * 수치가 달라지는 옵션은 [VariableOptionColor]로 표시한다. 나머지 옵션은 테마 글자 색상을 따른다.
 */
internal fun List<FormattedOptionSection>.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    this@toAnnotatedString.forEachIndexed { sectionIndex, section ->
        if (sectionIndex > 0) append("\n\n")
        section.title?.let { title ->
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(title) }
            append("\n")
        }
        section.lines.forEachIndexed { lineIndex, line ->
            if (lineIndex > 0) append("\n")
            if (line.isVariable) {
                withStyle(SpanStyle(color = VariableOptionColor)) { append(line.text) }
            } else {
                append(line.text)
            }
        }
    }
}
