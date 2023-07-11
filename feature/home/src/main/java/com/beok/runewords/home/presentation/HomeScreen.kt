package com.beok.runewords.home.presentation

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.beok.runewords.common.model.Rune
import com.beok.runewords.tracking.LocalTracker
import com.beok.runewords.tracking.TrackingConstants

@Composable
internal fun HomeScreen(onRuneClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeContent(onRuneClick = onRuneClick)
    }
}

@Composable
private fun HomeContent(onRuneClick: (String) -> Unit) {
    val tracking = LocalTracker.current
    Box(modifier = Modifier.fillMaxSize()) {
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
            color = MaterialTheme.colors.primary
        )
    }
}
