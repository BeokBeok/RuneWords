package com.beok.runewords.common.view

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * 처음 한 번은 툴팁을 자동으로 보여주는 상태. 사용자가 툴팁을 닫으면 다시 자동으로 보여주지 않는다.
 * 길게 누르면 언제든 툴팁을 볼 수 있다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberFirstTimeTooltipState(preferencesName: String, seenKey: String): TooltipState {
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE) }
    val tooltipState = rememberTooltipState(isPersistent = true)
    LaunchedEffect(Unit) {
        if (preferences.getBoolean(seenKey, false)) return@LaunchedEffect
        // 툴팁이 떠 있을 때 첫 터치는 툴팁을 닫는 데 쓰인다. 그래서 닫힌 시점에 본 것으로 저장한다.
        // show()는 바깥 터치로 닫혀도 반환되지 않으므로 별도 코루틴에서 실행하고 표시 상태만 관찰한다.
        launch { tooltipState.show() }
        snapshotFlow { tooltipState.isVisible }.first { visible -> visible }
        snapshotFlow { tooltipState.isVisible }.first { visible -> !visible }
        preferences.edit().putBoolean(seenKey, true).apply()
    }
    return tooltipState
}

/** 툴팁을 아이콘 아래에 둔다. 기본 위치(아이콘 위)는 상단 바에서 아이콘을 가린다. */
@Composable
fun rememberBelowAnchorPositionProvider(): PopupPositionProvider {
    val spacing = with(LocalDensity.current) { TooltipAnchorSpacing.roundToPx() }
    return remember(spacing) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset {
                val x = (anchorBounds.center.x - popupContentSize.width / 2)
                    .coerceIn(0, (windowSize.width - popupContentSize.width).coerceAtLeast(0))
                return IntOffset(x = x, y = anchorBounds.bottom + spacing)
            }
        }
    }
}

private val TooltipAnchorSpacing = 4.dp
