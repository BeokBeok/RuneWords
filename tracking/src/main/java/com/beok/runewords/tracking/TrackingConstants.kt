package com.beok.runewords.tracking

object TrackingConstants {

    object Rune {
        const val CLICK = "rune_click"
        const val INFO_CLICK = "rune_info_click"
        const val WORDS_CLICK = "rune_words_click"
    }

    object OptionFilter {
        const val OPEN = "rune_option_filter_open"
        const val APPLY = "rune_option_filter_apply"

        /** 적용할 때 고른 칩마다 하나씩 보낸다. GA4에서 옵션별 선택 수를 세기 위해서다. */
        const val OPTION = "rune_option_filter_option"
    }

    object Params {
        const val RUNE_NAME = "runeName"
        const val RUNE_WORDS_NAME = "runeWordsName"
        const val OPTIONS = "options"
        const val OPTION = "option"
        const val RESULT_COUNT = "resultCount"
    }
}
