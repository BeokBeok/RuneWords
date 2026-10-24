package com.beok.runewords.inapp.presentation

import com.beok.runewords.common.test.MainCoroutineTestExtension
import com.beok.runewords.inapp.domain.InAppRepository
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineTestExtension::class)
internal class InAppUpdateViewModelTest {

    @Test
    fun `3_9_0 사용자에게 강제 업데이트 버전이 3_10_0이면_UpdateRequired입니다`() {
        val viewModel = viewModel(forceUpdateVersion = Result.success("3.10.0"))

        viewModel.handleEvent(InAppUpdateContract.Event.CheckInAppUpdateType(version = "3.9.0"))

        assertEquals(expected = InAppUpdateContract.State.UpdateRequired, actual = viewModel.state.value)
    }

    @Test
    fun `현재 버전이 강제 업데이트 버전과 같으면_Ready입니다`() {
        val viewModel = viewModel(forceUpdateVersion = Result.success("3.10.0"))

        viewModel.handleEvent(InAppUpdateContract.Event.CheckInAppUpdateType(version = "3.10.0"))

        assertEquals(expected = InAppUpdateContract.State.Ready, actual = viewModel.state.value)
    }

    @Test
    fun `강제 업데이트 버전 형식이 잘못되면_Ready입니다`() {
        val viewModel = viewModel(forceUpdateVersion = Result.success(""))

        viewModel.handleEvent(InAppUpdateContract.Event.CheckInAppUpdateType(version = "3.9.0"))

        assertEquals(expected = InAppUpdateContract.State.Ready, actual = viewModel.state.value)
    }

    @Test
    fun `강제 업데이트 버전을 불러오지 못하면_Ready입니다`() {
        val viewModel = viewModel(forceUpdateVersion = Result.failure(IllegalStateException()))

        viewModel.handleEvent(InAppUpdateContract.Event.CheckInAppUpdateType(version = "3.9.0"))

        assertEquals(expected = InAppUpdateContract.State.Ready, actual = viewModel.state.value)
    }

    private fun viewModel(forceUpdateVersion: Result<String>): InAppUpdateViewModel =
        InAppUpdateViewModel(
            inAppRepository = object : InAppRepository {
                override suspend fun fetchForceUpdateVersion(): Result<String> = forceUpdateVersion
            }
        )
}
