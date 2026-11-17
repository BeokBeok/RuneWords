package com.beok.runewords.detail.presentation.option

import com.beok.runewords.detail.R
import com.beok.runewords.detail.presentation.option.XmlStringResources.Companion.EN
import com.beok.runewords.detail.presentation.option.XmlStringResources.Companion.KO
import com.beok.runewords.detail.presentation.option.XmlStringResources.Companion.placeholders
import com.beok.runewords.detail.presentation.option.XmlStringResources.Companion.resourceNameByID
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

internal class OptionStringResourceTest {

    private val referencedResIDs: List<Int> = OptionType.entries.map(OptionType::templateResID) +
        Skill.entries.map(Skill::nameResID) +
        OptionSectionTitle.entries.map(OptionSectionTitle::titleResID) +
        listOf(R.string.option_value_range, R.string.option_value_per_level, R.string.option_value_per_level_range)

    @Test
    fun `옵션 문자열은_en과 ko에 같은 이름으로 모두 있어야 한다`() {
        assertEquals(expected = EN.strings.keys, actual = KO.strings.keys)
    }

    @Test
    fun `옵션 형식이 쓰는 문자열은_모두 있어야 한다`() {
        referencedResIDs.map(resourceNameByID::getValue).forEach { name ->
            assertTrue(name in EN.strings, "values/에 $name 문자열이 없습니다.")
        }
    }

    @Test
    fun `옵션 문자열의 위치 지정자는_en과 ko가 같아야 한다`() {
        EN.strings.forEach { (name, template) ->
            assertEquals(
                expected = placeholders(template),
                actual = placeholders(KO.strings.getValue(name)),
                message = "$name 의 위치 지정자가 en과 ko에서 다릅니다."
            )
        }
    }

    @Test
    fun `옵션 문자열은_위치 지정자만 써야 한다`() {
        val allowed = Regex("""%%|%\d+\${'$'}s""")
        (EN.strings + KO.strings.mapKeys { "ko:${it.key}" }).forEach { (name, template) ->
            assertTrue('%' !in template.replace(allowed, ""), "$name 에 위치 지정자가 아닌 % 표기가 있습니다.")
        }
    }

    @Test
    fun `수치 표기 문자열은_정해진 개수의 인자를 받아야 한다`() {
        mapOf(
            R.string.option_value_range to setOf(1, 2),
            R.string.option_value_per_level to setOf(1),
            R.string.option_value_per_level_range to setOf(1, 2)
        ).forEach { (resID, expected) ->
            assertEquals(expected = expected, actual = placeholders(EN.template(resID)))
        }
    }

    @Test
    fun `룬워드 옵션 정의의 인자 개수는_옵션 형식의 위치 지정자 개수와 같아야 한다`() {
        RuneWordOptionCatalog.runeWordNames.forEach { runeWordName ->
            RuneWordOptionCatalog.find(runeWordName).orEmpty()
                .flatMap(RuneWordOptionSection::options)
                .forEach { option ->
                    assertEquals(
                        expected = (1..option.args.size).toSet(),
                        actual = placeholders(EN.template(option.type.templateResID)),
                        message = "$runeWordName 의 ${option.type} 인자 개수가 옵션 형식과 다릅니다."
                    )
                }
        }
    }
}
