package com.beok.runewords.inapp.domain

/**
 * "3.10.0" 형식의 버전을 숫자 단위로 비교합니다.
 * 자릿수가 다르면 부족한 자리를 0으로 보고 비교합니다. (3.10 == 3.10.0)
 */
internal class AppVersion private constructor(
    private val parts: List<Int>
) : Comparable<AppVersion> {

    override fun compareTo(other: AppVersion): Int {
        val size = maxOf(parts.size, other.parts.size)
        for (index in 0 until size) {
            val result = parts.getOrElse(index) { 0 }.compareTo(other.parts.getOrElse(index) { 0 })
            if (result != 0) return result
        }
        return 0
    }

    override fun equals(other: Any?): Boolean = other is AppVersion && compareTo(other) == 0

    override fun hashCode(): Int = parts.dropLastWhile { it == 0 }.hashCode()

    override fun toString(): String = parts.joinToString(separator = ".")

    companion object {

        /**
         * 형식이 잘못되면(빈 문자열, 숫자가 아닌 자리, 음수 등) null을 반환합니다.
         */
        fun parse(version: String): AppVersion? {
            val parts = version.trim()
                .split(".")
                .map { part -> part.toIntOrNull()?.takeIf { it >= 0 } ?: return null }
            return AppVersion(parts = parts)
        }
    }
}
