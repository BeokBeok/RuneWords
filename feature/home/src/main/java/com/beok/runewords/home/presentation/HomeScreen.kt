package com.beok.runewords.home.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beok.runewords.common.model.Rune
import com.beok.runewords.home.R

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()
        HomeContent()
    }
}

@Composable
private fun HomeTopBar() {
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.title_rune_list),
            modifier = Modifier.padding(start = 12.dp),
            fontSize = 20.sp
        )
    }
}

@Composable
private fun HomeContent() {
//    val context: Context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(Rune.all()) { item ->
                RuneItem(
                    item = item,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .clickable {
//                            context.startActivity(
//                                className = ActivityHomeView.CLASSNAME_COMBINATION,
//                                bundle = bundleOf(
//                                    BundleKeyConstants.RUNE_NAME to Rune.findByName(item.name)
//                                ),
//                                intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                            )
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
        Text(text = stringResource(id = item.nameResourceID))
    }
}
