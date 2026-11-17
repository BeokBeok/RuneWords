package com.beok.runewords.detail.presentation.option

import com.beok.runewords.detail.R
import java.io.File
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element

/**
 * 단위 테스트에서 실제 옵션 문자열 리소스 파일을 읽는다.
 * Android 리소스 시스템 없이 en, ko 문자열을 검증하기 위해 쓴다.
 */
internal class XmlStringResources(qualifier: String) {

    val strings: Map<String, String> = readStrings(File("src/main/res/$qualifier/$FILE_NAME"))

    fun getString(resID: Int, args: Array<out Any>): String {
        val name = resourceNameByID.getValue(resID)
        val template = strings[name] ?: error("$name 문자열이 없습니다.")
        return String.format(Locale.ROOT, template, *args)
    }

    fun template(resID: Int): String = strings.getValue(resourceNameByID.getValue(resID))

    companion object {
        private const val FILE_NAME = "strings_option_types.xml"

        val EN = XmlStringResources("values")
        val KO = XmlStringResources("values-ko")

        val resourceNameByID: Map<Int, String> = R.string::class.java.fields
            .associate { it.getInt(null) to it.name }

        private val PLACEHOLDER = Regex("""%(\d+)\${'$'}s""")

        /** 문자열의 위치 지정자 번호 목록. 예: "%2$s +%1$s" -> {1, 2} */
        fun placeholders(template: String): Set<Int> = PLACEHOLDER.findAll(template)
            .map { it.groupValues[1].toInt() }
            .toSet()

        private fun readStrings(file: File): Map<String, String> {
            val nodes = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(file)
                .getElementsByTagName("string")
            return (0 until nodes.length)
                .map { nodes.item(it) as Element }
                .associate { it.getAttribute("name") to it.textContent }
        }
    }
}
