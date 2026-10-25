package com.beok.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Baseline Profile을 수집한다. 기기 언어는 영어여야 한다.
 *
 * ./gradlew :app:generateBaselineProfile
 */
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collect(
        packageName = TARGET_PACKAGE_NAME,
        includeInStartupProfile = true
    ) {
        startActivityAndWait()
        openCombination()
        openDetail()
    }

    private fun MacrobenchmarkScope.openCombination() {
        device.wait(Until.hasObject(By.text(RUNE)), TIMEOUT_MS)
        device.findObject(By.text(RUNE)).click()
        device.wait(Until.hasObject(By.text(RUNE_WORDS)), TIMEOUT_MS)
    }

    private fun MacrobenchmarkScope.openDetail() {
        device.findObject(By.text(RUNE_WORDS)).click()
        // 전면 광고가 열리면 뒤로 가기로 닫는다.
        if (!device.wait(Until.hasObject(By.text(DETAIL_TITLE)), TIMEOUT_MS)) {
            device.pressBack()
            device.wait(Until.hasObject(By.text(DETAIL_TITLE)), TIMEOUT_MS)
        }
    }

    private companion object {
        const val RUNE = "Amn"
        const val RUNE_WORDS = "Call to Arms"
        const val DETAIL_TITLE = "Option"
        const val TIMEOUT_MS = 10_000L
    }
}
