package com.beok.runewords.detail.data

import com.beok.runewords.detail.data.local.RuneWordsDetailDAO
import com.beok.runewords.detail.data.model.RuneWordsDetailResponse
import com.beok.runewords.detail.data.model.RuneWordsDetailTable
import com.beok.runewords.detail.data.remote.RuneWordsDetailRemoteDataSource
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class RuneWordsDetailRepositoryImplTest {

    private val runeWordsName = "call_to_arms"
    private val remoteResponse = RuneWordsDetailResponse(
        name = runeWordsName,
        rune_combination = listOf("amn", "ral", "mal", "ist", "ohm"),
        type = listOf("all_weapons"),
        option = "rune_word_call_to_arms_option",
        level_limit = 57
    )
    private val remoteDetail = remoteResponse.toDomain()
    private val cachedDetail = remoteDetail.copy(levelLimit = 1)

    @Test
    fun `캐시가 없으면 원격 데이터를 내보내고 캐시에 저장합니다`() = runTest {
        val localDataSource = FakeRuneWordsDetailDAO()
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { remoteResponse }
        )

        val actual = repository.fetchInfo(name = runeWordsName).first()

        assertEquals(expected = remoteDetail, actual = actual)
        assertEquals(
            expected = listOf(RuneWordsDetailTable.fromDomain(remoteDetail)),
            actual = localDataSource.tables.value
        )
    }

    @Test
    fun `캐시가 있으면 캐시를 먼저 내보내고 원격 데이터로 갱신합니다`() = runTest {
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = FakeRuneWordsDetailDAO(RuneWordsDetailTable.fromDomain(cachedDetail)),
            remoteDataSource = FakeRemoteDataSource { remoteResponse }
        )

        val actual = repository.fetchInfo(name = runeWordsName).take(2).toList()

        assertEquals(expected = listOf(cachedDetail, remoteDetail), actual = actual)
    }

    @Test
    fun `캐시가 없고 원격 요청이 실패하면 예외를 전달합니다`() = runTest {
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = FakeRuneWordsDetailDAO(),
            remoteDataSource = FakeRemoteDataSource { throw IllegalStateException() }
        )

        assertFailsWith<IllegalStateException> {
            repository.fetchInfo(name = runeWordsName).first()
        }
    }

    @Test
    fun `캐시가 있고 원격 요청이 실패하면 캐시를 내보낸 뒤 예외를 전달합니다`() = runTest {
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = FakeRuneWordsDetailDAO(RuneWordsDetailTable.fromDomain(cachedDetail)),
            remoteDataSource = FakeRemoteDataSource { throw IllegalStateException() }
        )
        val emitted = mutableListOf<RuneWordsDetail>()

        assertFailsWith<IllegalStateException> {
            repository.fetchInfo(name = runeWordsName).collect { emitted.add(it) }
        }
        assertEquals(expected = listOf(cachedDetail), actual = emitted)
    }

    private class FakeRuneWordsDetailDAO(vararg initial: RuneWordsDetailTable) :
        RuneWordsDetailDAO {

        val tables = MutableStateFlow(initial.toList())

        override suspend fun insert(obj: RuneWordsDetailTable) {
            tables.value = tables.value.filterNot { it.name == obj.name } + obj
        }

        override fun findBy(name: String): Flow<List<RuneWordsDetailTable>> =
            tables.map { tables -> tables.filter { it.name == name } }
    }

    private class FakeRemoteDataSource(private val response: () -> RuneWordsDetailResponse) :
        RuneWordsDetailRemoteDataSource {

        override suspend fun fetchInfo(name: String): RuneWordsDetailResponse = response()
    }
}
