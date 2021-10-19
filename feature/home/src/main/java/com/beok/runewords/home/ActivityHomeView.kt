package com.beok.runewords.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.startActivity

object ActivityHomeView {

    private const val CLASSNAME_COMBINATION =
        "com.beok.runewords.combination.presenter.CombinationActivity"

    @Composable
    fun Layout(context: Context) {
        MaterialTheme {
            Content(context)
        }
    }

    @Composable
    private fun Content(context: Context) {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            items(Rune.all()) { item ->
                RuneItem(
                    item = item,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .clickable {
                            context.startActivity(
                                className = CLASSNAME_COMBINATION,
                                bundle = bundleOf(BundleKeyConstants.RUNE_NAME to item.name)
                            )
                        }
                )
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
            Text(text = stringResource(id = item.nameResourceID))
        }
    }
}
