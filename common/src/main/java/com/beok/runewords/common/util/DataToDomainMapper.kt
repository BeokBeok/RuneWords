package com.beok.runewords.common.util

interface DataToDomainMapper<T> {

    fun toDomain(): T
}

fun <T> List<DataToDomainMapper<T>>.toDomain(): List<T> = map { it.toDomain() }
