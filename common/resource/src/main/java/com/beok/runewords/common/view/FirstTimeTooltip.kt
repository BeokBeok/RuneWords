package com.beok.runewords.common.view

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipScope
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.findRootCoordinates
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
                val x = belowAnchorLeft(
                    anchorCenterX = anchorBounds.center.x.toFloat(),
                    tooltipWidth = popupContentSize.width.toFloat(),
                    windowWidth = windowSize.width.toFloat()
                )
                return IntOffset(x = x.toInt(), y = anchorBounds.bottom + spacing)
            }
        }
    }
}

/** 툴팁을 앵커 가운데에 맞추되 창 밖으로 나가지 않게 한다. 툴팁 위치와 꼬리 위치가 같은 식을 쓴다. */
private fun belowAnchorLeft(anchorCenterX: Float, tooltipWidth: Float, windowWidth: Float): Float =
    (anchorCenterX - tooltipWidth / 2).coerceIn(0f, (windowWidth - tooltipWidth).coerceAtLeast(0f))

/**
 * [rememberBelowAnchorPositionProvider]와 함께 쓰는 툴팁. 위쪽 꼬리가 앵커 가운데를 가리킨다.
 * M3 PlainTooltip의 caretSize는 앵커 위에 공간이 있으면 꼬리를 아래에 그려서 직접 그린다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipScope.BelowAnchorTooltip(text: String) {
    val caretColor = TooltipDefaults.plainTooltipContainerColor
    Column(
        modifier = Modifier
            .drawCaret { anchor ->
                val caretWidth = CaretSize.width.toPx()
                val caretHeight = CaretSize.height.toPx()
                // 팝업 창의 화면 좌표는 실제 위치와 어긋난다. 그래서 위치 제공자와 같은 식으로 툴팁 왼쪽을 다시 구한다.
                val anchorBounds = anchor?.boundsInWindow()
                val caretX = if (anchorBounds == null) {
                    size.width / 2
                } else {
                    val tooltipLeft = belowAnchorLeft(
                        anchorCenterX = anchorBounds.center.x,
                        tooltipWidth = size.width,
                        windowWidth = anchor.findRootCoordinates().size.width.toFloat()
                    )
                    anchorBounds.center.x - tooltipLeft
                }.coerceIn(caretWidth / 2, size.width - caretWidth / 2)
                val caret = Path().apply {
                    moveTo(caretX - caretWidth / 2, caretHeight)
                    lineTo(caretX, 0f)
                    lineTo(caretX + caretWidth / 2, caretHeight)
                    close()
                }
                onDrawBehind { drawPath(path = caret, color = caretColor) }
            }
            .padding(top = CaretSize.height)
    ) {
        PlainTooltip {
            Text(text = text)
        }
    }
}

private val TooltipAnchorSpacing = 4.dp

@OptIn(ExperimentalMaterial3Api::class)
private val CaretSize = TooltipDefaults.caretSize
