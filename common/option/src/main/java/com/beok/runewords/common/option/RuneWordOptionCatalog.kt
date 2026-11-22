package com.beok.runewords.common.option

/**
 * 룬워드별 옵션 정의를 찾는다. 키는 Firestore의 룬워드 `name`이다.
 *
 * 정의는 [runeWordOptionDefinitions]에 있다.
 * 새 룬워드를 추가하면 [runeWordOptionDefinitions]에 정의를 추가한다. 정의가 없으면 옵션을 표시하지 않는다.
 */
object RuneWordOptionCatalog {

    fun find(runeWordName: String): List<RuneWordOptionSection>? = runeWordOptionDefinitions[runeWordName]

    val runeWordNames: Set<String> get() = runeWordOptionDefinitions.keys
}
