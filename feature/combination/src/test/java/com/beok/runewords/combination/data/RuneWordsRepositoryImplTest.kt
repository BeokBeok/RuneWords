package com.beok.runewords.combination.data

import com.beok.runewords.combination.data.local.RuneDAO
import com.beok.runewords.combination.data.model.RuneTable
import com.beok.runewords.combination.data.model.RuneWordsCache
import com.beok.runewords.combination.data.model.RuneWordsResponse
import com.beok.runewords.combination.data.remote.RuneWordsRemoteDataSource
import com.beok.runewords.combination.domain.model.GameMode
import com.beok.runewords.combination.domain.model.RuneWords
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

internal class RuneWordsRepositoryImplTest {

    private val rune = "zod"
    private val remoteResponse = listOf(
        RuneWordsResponse(name = "breath_of_the_dying"),
        RuneWordsResponse(name = "obsession"),
        RuneWordsResponse(name = "mosaic", mode = "standard"),
        RuneWordsResponse(name = "hustle", mode = "ladder"),
        RuneWordsResponse(name = "unknown_mode", mode = "hardcore")
    )
    private val remoteRuneWords = listOf(
        RuneWords(name = "breath_of_the_dying"),
        RuneWords(name = "obsession"),
        RuneWords(name = "mosaic", mode = GameMode.STANDARD),
        RuneWords(name = "hustle", mode = GameMode.LADDER),
        RuneWords(name = "unknown_mode")
    )

    @Test
    fun `캐시가 없으면 원격 데이터를 내보내고 캐시에 저장합니다`() = runTest {
        val localDataSource = FakeRuneDAO()
        val repository = RuneWordsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { remoteResponse }
        )

        val actual = repository.searchByRune(rune = rune).first()

        assertEquals(expected = remoteRuneWords, actual = actual)
        assertEquals(
            expected = listOf(
                RuneTable(name = rune, runewords = remoteRuneWords.map(RuneWordsCache::from))
            ),
            actual = localDataSource.tables.value
        )
    }

    @Test
    fun `캐시가 있으면 캐시를 먼저 내보내고 원격 데이터로 갱신합니다`() = runTest {
        val localDataSource = FakeRuneDAO(
            RuneTable(name = rune, runewords = listOf(RuneWordsCache(name = "obsession")))
        )
        val repository = RuneWordsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource { remoteResponse }
        )

        val actual = repository.searchByRune(rune = rune).take(2).toList()

        assertEquals(
            expected = listOf(listOf(RuneWords(name = "obsession")), remoteRuneWords),
            actual = actual
        )
    }

    @Test
    fun `원격에서 게임 모드가 바뀌면 캐시를 갱신하고 바뀐 모드를 내보냅니다`() = runTest {
        val localDataSource = FakeRuneDAO(
            RuneTable(
                name = rune,
                runewords = listOf(RuneWordsCache(name = "hustle", mode = GameMode.LADDER))
            )
        )
        val repository = RuneWordsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = FakeRemoteDataSource {
                listOf(RuneWordsResponse(name = "hustle", mode = "standard"))
            }
        )

        val actual = repository.searchByRune(rune = rune).take(2).toList()

        assertEquals(
            expected = listOf(
                listOf(RuneWords(name = "hustle", mode = GameMode.LADDER)),
                listOf(RuneWords(name = "hustle", mode = GameMode.STANDARD))
            ),
            actual = actual
        )
        assertEquals(
            expected = listOf(
                RuneTable(
                    name = rune,
                    runewords = listOf(RuneWordsCache(name = "hustle", mode = GameMode.STANDARD))
                )
            ),
            actual = localDataSource.tables.value
        )
    }

    @Test
    fun `캐시가 없고 원격 요청이 실패하면 예외를 전달합니다`() = runTest {
        val repository = RuneWordsRepositoryImpl(
            localDataSource = FakeRuneDAO(),
            remoteDataSource = FakeRemoteDataSource { throw IllegalStateException() }
        )

        assertFailsWith<IllegalStateException> {
            repository.searchByRune(rune = rune).first()
        }
    }

    @Test
    fun `캐시가 있고 원격 요청이 실패하면 캐시를 내보낸 뒤 예외를 전달합니다`() = runTest {
        val repository = RuneWordsRepositoryImpl(
            localDataSource = FakeRuneDAO(
                RuneTable(name = rune, runewords = listOf(RuneWordsCache(name = "obsession")))
            ),
            remoteDataSource = FakeRemoteDataSource { throw IllegalStateException() }
        )
        val emitted = mutableListOf<List<RuneWords>>()

        assertFailsWith<IllegalStateException> {
            repository.searchByRune(rune = rune).collect { emitted.add(it) }
        }
        assertEquals(expected = listOf(listOf(RuneWords(name = "obsession"))), actual = emitted)
    }

    private class FakeRuneDAO(vararg initial: RuneTable) : RuneDAO {

        val tables = MutableStateFlow(initial.toList())

        override suspend fun insert(obj: RuneTable) {
            tables.value = tables.value.filterNot { it.name == obj.name } + obj
        }

        override fun findRuneWordsBy(rune: String): Flow<List<RuneTable>> =
            tables.map { tables -> tables.filter { it.name == rune } }
    }

    private class FakeRemoteDataSource(private val response: () -> List<RuneWordsResponse>) :
        RuneWordsRemoteDataSource {

        override suspend fun searchByRune(rune: String): List<RuneWordsResponse> = response()
    }
}
