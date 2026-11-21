package com.beok.runewords.home.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupPositionProvider
import androidx.core.content.edit
import androidx.core.os.bundleOf
import com.beok.runewords.common.model.Rune
import com.beok.runewords.home.R
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.TrackingConstants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    onRuneClick: (String) -> Unit,
    onRankingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HomeTopBar(onRankingClick = onRankingClick) }
    ) { contentPadding ->
        HomeContent(
            modifier = Modifier.padding(contentPadding),
            onRuneClick = onRuneClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(onRankingClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            RankingAction(onRankingClick = onRankingClick)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

/**
 * 랭킹 아이콘. 처음 한 번은 툴팁을 자동으로 보여준다. 사용자가 툴팁을 닫으면 다시 자동으로 보여주지 않는다.
 * 길게 누르면 언제든 툴팁을 볼 수 있다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RankingAction(onRankingClick: () -> Unit) {
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE) }
    val tooltipState = rememberTooltipState(isPersistent = true)
    LaunchedEffect(Unit) {
        if (preferences.getBoolean(KeyRankingTooltipSeen, false)) return@LaunchedEffect
        // 툴팁이 떠 있을 때 첫 터치는 툴팁을 닫는 데 쓰인다. 그래서 닫힌 시점에 본 것으로 저장한다.
        // show()는 바깥 터치로 닫혀도 반환되지 않으므로 별도 코루틴에서 실행하고 표시 상태만 관찰한다.
        launch { tooltipState.show() }
        snapshotFlow { tooltipState.isVisible }.first { visible -> visible }
        snapshotFlow { tooltipState.isVisible }.first { visible -> !visible }
        preferences.edit { putBoolean(KeyRankingTooltipSeen, true) }
    }
    TooltipBox(
        positionProvider = rememberBelowAnchorPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(text = stringResource(id = R.string.home_ranking_tooltip))
            }
        },
        state = tooltipState
    ) {
        IconButton(
            onClick = {
                onRankingClick()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ranking),
                contentDescription = stringResource(id = R.string.home_ranking),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/** 툴팁을 아이콘 아래에 둔다. 기본 위치(아이콘 위)는 상단 바에서 아이콘을 가린다. */
@Composable
private fun rememberBelowAnchorPositionProvider(): PopupPositionProvider {
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
private const val PreferencesName = "home"
private const val KeyRankingTooltipSeen = "ranking_tooltip_seen"

@Composable
private fun HomeContent(modifier: Modifier, onRuneClick: (String) -> Unit) {
    val tracking = LocalTracker.current
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(Rune.all()) { item ->
                RuneItem(
                    item = item,
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(shape = RoundedCornerShape(size = 20.dp))
                        .clickable {
                            onRuneClick(item.name)
                            tracking.logEvent(
                                name = TrackingConstants.Rune.CLICK,
                                bundle = bundleOf(TrackingConstants.Params.RUNE_NAME to item.name)
                            )
                        }
                )
            }
        }
    }
}

@Composable
private fun RuneItem(item: Rune, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.iconResourceID),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = item.nameResourceID),
            color = MaterialTheme.colorScheme.primary
        )
    }
}
