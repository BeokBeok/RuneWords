package com.beok.runewords.detail.presenter

import com.beok.runewords.detail.InstantExecutorExtension
import com.beok.runewords.detail.domain.RuneWordsDetailFetchUseCase
import com.beok.runewords.detail.domain.model.RuneWordsDetail
import com.beok.runewords.detail.presenter.vo.RuneWordsVO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [InstantExecutorExtension::class])
internal class DetailViewModelTest {

    private val runeWordsDetailFetchUseCase: RuneWordsDetailFetchUseCase = mockk(relaxed = true)
    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setup() {
        viewModel = DetailViewModel(runeWordsDetailFetchUseCase = runeWordsDetailFetchUseCase)
    }

    @Test
    fun `룬워드 아이템의 상세 정보를 불러옵니다`() = runBlocking {
        val runeWordsName = "call_to_arms"
        val mockResponse: RuneWordsDetail = mockk(relaxed = true)
        coEvery {
            runeWordsDetailFetchUseCase
                .execute(name = runeWordsName)
                .getOrNull()
        } returns mockResponse

        viewModel.fetchDetail(name = runeWordsName)

        assertEquals(RuneWordsVO.fromDto(mockResponse), viewModel.detailInfo.value)
    }
}
