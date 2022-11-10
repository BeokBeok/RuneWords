package com.beok.runewords.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.ext.startActivity
import com.beok.runewords.common.model.Rune

internal object ActivityHomeView {

    private const val CLASSNAME_COMBINATION =
        "com.beok.runewords.combination.presentation.CombinationActivity"

    @Composable
    fun Layout(
        context: Context,
        runeClickTracking: (String) -> Unit,
    ) {
        MaterialTheme {
            HomeScaffold(
                context = context,
                runeClickTracking = runeClickTracking,
            )
        }
    }

    @Composable
    private fun HomeScaffold(
        context: Context,
        runeClickTracking: (String) -> Unit,
    ) {
        Scaffold(
            scaffoldState = rememberScaffoldState(snackbarHostState = SnackbarHostState()),
            topBar = { HomeTopBar() },
        ) { paddings ->
            HomeContent(
                modifier = Modifier.padding(paddings),
                context = context,
                runeClickTracking = runeClickTracking
            )
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
    private fun HomeContent(
        modifier: Modifier,
        context: Context,
        runeClickTracking: (String) -> Unit
    ) {
        Box(modifier = modifier.fillMaxSize()) {
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
                                context.startActivity(
                                    className = CLASSNAME_COMBINATION,
                                    bundle = bundleOf(
                                        BundleKeyConstants.RUNE_NAME to Rune.findByName(item.name)
                                    ),
                                    intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                )
                                runeClickTracking(item.name)
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
}
