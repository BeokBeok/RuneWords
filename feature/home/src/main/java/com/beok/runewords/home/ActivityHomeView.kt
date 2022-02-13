package com.beok.runewords.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.os.bundleOf
import com.beok.runewords.common.BundleKeyConstants
import com.beok.runewords.common.ext.startActivity
import com.beok.runewords.common.model.Rune
import com.beok.runewords.home.inapp.InAppUpdateState
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

internal object ActivityHomeView {

    private const val CLASSNAME_COMBINATION =
        "com.beok.runewords.combination.presenter.CombinationActivity"

    @Composable
    fun Layout(
        context: Context,
        runeClickTracking: (String) -> Unit,
        inAppUpdateState: InAppUpdateState,
        updateAction: () -> Unit
    ) {
        MaterialTheme {
            HomeScaffold(
                context = context,
                runeClickTracking = runeClickTracking,
                inAppUpdateState = inAppUpdateState,
                updateAction = updateAction
            )
        }
    }

    @Composable
    private fun HomeScaffold(
        context: Context,
        runeClickTracking: (String) -> Unit,
        inAppUpdateState: InAppUpdateState,
        updateAction: () -> Unit
    ) {
        Scaffold(
            scaffoldState = rememberScaffoldState(snackbarHostState = SnackbarHostState()),
            snackbarHost = { InAppUpdateSnackBar(inAppUpdateState, updateAction) },
            topBar = { HomeTopBar() },
        ) {
            HomeContent(context = context, runeClickTracking = runeClickTracking)
        }
    }

    @Composable
    private fun InAppUpdateSnackBar(
        inAppUpdateState: InAppUpdateState,
        updateAction: () -> Unit
    ) {
        if (inAppUpdateState == InAppUpdateState.Downloaded) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = { updateAction() }) {
                        Text(text = stringResource(id = R.string.install))
                    }
                },
                content = {
                    Text(text = stringResource(id = R.string.complete_download_for_update))
                }
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
    private fun HomeContent(context: Context, runeClickTracking: (String) -> Unit) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (content, admob) = createRefs()
            LazyVerticalGrid(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(admob.top)
                },
                cells = GridCells.Adaptive(minSize = 128.dp)
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
                                    )
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
