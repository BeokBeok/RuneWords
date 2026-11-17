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
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
    fun `캐시가 있고 원격 요청이 실패하면 캐시를 유지합니다`() = runTest {
        val localDataSource = FakeRuneWordsDetailDAO(RuneWordsDetailTable.fromDomain(cachedDetail))
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { throw IllegalStateException() }
        )
        val emitted = mutableListOf<RuneWordsDetail>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.fetchInfo(name = runeWordsName).collect { emitted.add(it) }
        }

        assertEquals(expected = listOf(cachedDetail), actual = emitted)
        assertEquals(
            expected = listOf(RuneWordsDetailTable.fromDomain(cachedDetail)),
            actual = localDataSource.tables.value
        )
    }

    @Test
    fun `캐시가 없고 원격 문서가 없으면 저장하지 않고 예외를 전달합니다`() = runTest {
        val localDataSource = FakeRuneWordsDetailDAO()
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { RuneWordsDetailResponse() }
        )

        assertFailsWith<IllegalStateException> {
            repository.fetchInfo(name = runeWordsName).first()
        }
        assertEquals(expected = emptyList(), actual = localDataSource.tables.value)
    }

    @Test
    fun `캐시가 있고 원격 문서가 없으면 캐시를 유지합니다`() = runTest {
        val localDataSource = FakeRuneWordsDetailDAO(RuneWordsDetailTable.fromDomain(cachedDetail))
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { RuneWordsDetailResponse() }
        )
        val emitted = mutableListOf<RuneWordsDetail>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.fetchInfo(name = runeWordsName).collect { emitted.add(it) }
        }

        assertEquals(expected = listOf(cachedDetail), actual = emitted)
        assertEquals(
            expected = listOf(RuneWordsDetailTable.fromDomain(cachedDetail)),
            actual = localDataSource.tables.value
        )
    }

    @Test
    fun `이름 끝에 밑줄이 붙은 룬워드는_밑줄 없는 이름으로 조회하고 다시 요청하지 않습니다`() = runTest {
        val localDataSource = FakeRuneWordsDetailDAO()
        var remoteCallCount = 0
        val voidResponse = remoteResponse.copy(name = "void_", option = "rune_word_void_option")
        val repository = RuneWordsDetailRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource {
                remoteCallCount++
                voidResponse
            }
        )
        val emitted = mutableListOf<RuneWordsDetail>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.fetchInfo(name = "void").collect { emitted.add(it) }
        }

        assertEquals(expected = "void_", actual = emitted.first().name)
        assertEquals(expected = listOf("void_"), actual = localDataSource.tables.value.map { it.name })
        // 최초 요청 1회 + 캐시 발행 후 동기화 1회. 그 뒤로는 같은 값이라 저장하지 않으므로 반복되지 않는다.
        assertEquals(expected = 2, actual = remoteCallCount)
    }

    private class FakeRuneWordsDetailDAO(vararg initial: RuneWordsDetailTable) :
        RuneWordsDetailDAO {

        val tables = MutableStateFlow(initial.toList())

        override suspend fun insert(obj: RuneWordsDetailTable) {
            tables.value = tables.value.filterNot { it.name == obj.name } + obj
        }

        override fun findBy(name: String): Flow<List<RuneWordsDetailTable>> =
            tables.map { tables -> tables.filter { it.name == name || it.name == "${name}_" } }
    }

    private class FakeRemoteDataSource(private val response: () -> RuneWordsDetailResponse) :
        RuneWordsDetailRemoteDataSource {

        override suspend fun fetchInfo(name: String): RuneWordsDetailResponse = response()
    }
}
